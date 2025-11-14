package com.java.controller;

import com.java.modules.User;
import com.java.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        log.info("UserController :: createUser {}",user.getEmail());
        return userService.save(user);
    }

    @GetMapping("/{userId}")
    public User fetchUser(@PathVariable Integer userId) {
        log.info("UserController :: fetchUser {}", userId);
        return userService.findById(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Integer userId, @RequestBody User user) {
        log.info("UserController :: updateUser {} {}", userId, user.getFirstName());
        return userService.update(userId, user);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer userId) {
        log.info("UserController :: deleteUser {}", userId);
        userService.delete(userId);
    }
}
