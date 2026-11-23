package com.example.springdto.controller;

import com.example.springdto.dto.UserDTO;
import com.example.springdto.exception.InvalidSortException;
import com.example.springdto.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "User management")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        log.info("Creating user: {}", userDTO.getEmailAddress());
        return userService.save(userDTO);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Integer id) {
        log.info("Fetching user with id: {}", id);
        return userService.findById(id);
    }

    @GetMapping
    public Page<UserDTO> getAllUsers(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(1) @Max(100) int size,
            @Parameter(description = "Field and optional direction, e.g. fName,asc. "
                    + "For multiple fields separate with ';' e.g. fName,asc;id,desc. "
                    + "Allowed fields: id, fName, lName, emailAddress, phone")
            @RequestParam(required = false) String sort) {
        log.info("Fetching users page: {}, size: {}, sort: {}", page, size, sort);
        return userService.findAll(PageRequest.of(page, size, toEntitySort(sort)));
    }

    private static final Map<String, String> SORT_FIELDS = Map.of(
            "id", "userId",
            "fName", "firstName",
            "lName", "lastName",
            "emailAddress", "email",
            "phone", "phone");

    // Maps public DTO field names to entity fields; blank sort values are ignored
    private Sort toEntitySort(String sort) {
        if (sort == null || sort.isBlank()) {
            return Sort.unsorted();
        }
        List<Sort.Order> orders = new ArrayList<>();
        for (String part : sort.split(";")) {
            if (part.isBlank()) {
                continue;
            }
            String[] tokens = part.split(",");
            String property = tokens[0].trim();
            String field = SORT_FIELDS.get(property);
            if (field == null) {
                throw new InvalidSortException("Cannot sort by '" + property
                        + "'. Allowed fields: " + new TreeSet<>(SORT_FIELDS.keySet()));
            }
            Sort.Direction direction = tokens.length > 1
                    ? Sort.Direction.fromOptionalString(tokens[1].trim()).orElse(Sort.Direction.ASC)
                    : Sort.Direction.ASC;
            orders.add(new Sort.Order(direction, field));
        }
        return Sort.by(orders);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Integer id, @Valid @RequestBody UserDTO userDTO) {
        log.info("Updating user with id: {}", id);
        return userService.update(id, userDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer id) {
        log.info("Deleting user with id: {}", id);
        userService.delete(id);
    }
}
