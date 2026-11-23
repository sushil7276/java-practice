package com.example.resttemplate.controller;

import com.example.resttemplate.dto.PageResponse;
import com.example.resttemplate.dto.UserDTO;
import com.example.resttemplate.service.UserServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserControllerConsumer {

    private final UserServiceClient userServiceClient;

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userServiceClient.createUser(userDTO);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@RequestBody UserDTO userDTO, @PathVariable Integer id) {
        return userServiceClient.updateUser(userDTO, id);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Integer id) {
        return userServiceClient.getUser(id);
    }

    @GetMapping
    public PageResponse<UserDTO> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort) {
        return userServiceClient.getUsers(page, size, sort);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userServiceClient.deleteUser(id);
    }
}
