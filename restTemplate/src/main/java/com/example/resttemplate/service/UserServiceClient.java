package com.example.resttemplate.service;

import com.example.resttemplate.dto.PageResponse;
import com.example.resttemplate.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceClient {

    private static final String ID_PATH = "/{id}";

    private final RestClient restClient;

    @Value("${user.endpoint}")
    private String url;

    public UserDTO createUser(UserDTO userDTO) {
        return execute("creating user", () -> restClient.post().uri(url)
                .body(userDTO)
                .retrieve()
                .body(UserDTO.class));
    }

    public UserDTO updateUser(UserDTO userDTO, Integer id) {
        return execute("updating user", () -> restClient.put().uri(url + ID_PATH, id)
                .body(userDTO)
                .retrieve()
                .body(UserDTO.class));
    }

    public UserDTO getUser(Integer id) {
        return execute("fetching user", () -> restClient.get().uri(url + ID_PATH, id)
                .retrieve()
                .body(UserDTO.class));
    }

    public PageResponse<UserDTO> getUsers(int page, int size, String sort) {
        return execute("fetching users", () -> restClient.get()
                .uri(url, uriBuilder -> {
                    uriBuilder.queryParam("page", page).queryParam("size", size);
                    if (sort != null && !sort.isBlank()) {
                        uriBuilder.queryParam("sort", sort);
                    }
                    return uriBuilder.build();
                })
                .retrieve()
                .body(new ParameterizedTypeReference<PageResponse<UserDTO>>() {}));
    }

    public void deleteUser(Integer id) {
        execute("deleting user", () -> restClient.delete().uri(url + ID_PATH, id)
                .retrieve()
                .toBodilessEntity());
    }

    private <T> T execute(String action, Supplier<T> call) {
        try {
            return call.get();
        } catch (HttpStatusCodeException e) {
            log.error("Error while {}: status={}, body={}", action, e.getStatusCode(),
                    e.getResponseBodyAsString(), e);
            throw new RuntimeException("Error while " + action + ": " + e.getStatusCode()
                    + " " + e.getResponseBodyAsString(), e);
        } catch (ResourceAccessException e) {
            log.error("Cannot reach {} while {}: {}", url, action, e.getMessage(), e);
            throw new RuntimeException("Cannot reach " + url + " while " + action + ": " + e.getMessage(), e);
        } catch (Exception e) {
            log.error("Unexpected error while {}: {}", action, e.getMessage(), e);
            throw new RuntimeException("Unexpected error while " + action + ": " + e.getMessage(), e);
        }
    }
}
