package com.example.springdto.serviceImpl;

import com.example.springdto.dto.UserDTO;
import com.example.springdto.exception.UserNotFoundException;
import com.example.springdto.mapper.UserMapper;
import com.example.springdto.modules.User;
import com.example.springdto.repository.UserRepository;
import com.example.springdto.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDTO save(UserDTO userDTO) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDTO)));
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }

    @Override
    public UserDTO findById(Integer id) {
        return userMapper.toDto(getUser(id));
    }

    @Override
    @Transactional
    public UserDTO update(Integer id, UserDTO userDTO) {
        User dbUser = getUser(id);
        userMapper.updateEntity(userDTO, dbUser);
        return userMapper.toDto(dbUser);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User Not Found");
        }
        userRepository.deleteById(id);
    }

    private User getUser(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }
}
