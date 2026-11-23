package com.example.springdto.service;

import com.example.springdto.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDTO save(UserDTO userDTO);

    Page<UserDTO> findAll(Pageable pageable);

    UserDTO findById(Integer id);

    UserDTO update(Integer id, UserDTO userDTO);

    void delete(Integer id);
}
