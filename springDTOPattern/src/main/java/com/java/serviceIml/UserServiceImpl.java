package com.java.serviceIml;

import com.java.dto.UserDTO;
import com.java.exception.UserNotFoundException;
import com.java.mapper.UserMapper;
import com.java.modules.User;
import com.java.repository.UserRepository;
import com.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userMapper.dtoToEntity(userDTO);
        userRepository.save(user);
        return userMapper.entityToDto(user);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = userList.stream()
                .map(user -> userMapper.entityToDto(user))
                .collect(Collectors.toList());

        return userDTOList;
    }

    @Override
    public UserDTO findById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Not Found."));

        return userMapper.entityToDto(user);
    }

    @Override
    public UserDTO update(Integer userId, UserDTO userDTO) {
        User dbUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Not Found."));

        dbUser.setFirstName(userDTO.getFirstName());
        dbUser.setLastName(userDTO.getLastName());
        dbUser.setEmail(userDTO.getEmailAddress());
        dbUser.setPhone(userDTO.getPhone());
        userRepository.save(dbUser);

        return userMapper.entityToDto(dbUser);
    }

    @Override
    public void delete(Integer userId) {
        userRepository.deleteById(userId);
    }
}
