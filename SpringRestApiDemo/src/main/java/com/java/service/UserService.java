package com.java.service;

import com.java.modules.User;

public interface UserService {
    User save(User user);

    User findById(Integer userId);

    User update(Integer userId, User user);

    void delete(Integer userId);
}
