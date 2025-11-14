package com.java.service;

import com.java.dto.UserDTO;
import com.java.modules.User;

import java.util.List;

public interface UserService {
    UserDTO save(UserDTO userDTO);

    List<UserDTO> findAll();

    UserDTO findById(Integer id);

    UserDTO update(Integer id, UserDTO userDTO);

    void delete(Integer id);
}
