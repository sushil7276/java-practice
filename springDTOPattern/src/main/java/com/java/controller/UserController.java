package com.java.controller;

import com.java.dto.UserDTO;
import com.java.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        log.info("UserController :: createUser {}", userDTO.getEmailAddress());
        return userService.save(userDTO);
    }

    @GetMapping
    public List<UserDTO> findAllUser() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public UserDTO fetchUser(@PathVariable Integer userId) {
        log.info("UserController :: fetchUser {}", userId);
        return userService.findById(userId);
    }

    @PutMapping("/{userId}")
    public UserDTO updateUser(@PathVariable Integer userId, @Valid @RequestBody UserDTO userDTO) {
        log.info("UserController :: updateUser {} {}", userId, userDTO.getFirstName());
        return userService.update(userId, userDTO);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer userId) {
        log.info("UserController :: deleteUser {}", userId);
        userService.delete(userId);
    }
}
