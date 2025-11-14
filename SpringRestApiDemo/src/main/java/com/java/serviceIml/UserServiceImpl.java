package com.java.serviceIml;

import com.java.modules.User;
import com.java.repository.UserRepository;
import com.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public User update(Integer userId, User user) {
        User dbUser = findById(userId);

        dbUser.setFirstName(user.getFirstName());
        dbUser.setLastName(user.getLastName());
        dbUser.setEmail(user.getEmail());
        dbUser.setPhone(user.getPhone());

        userRepository.save(dbUser);

        return dbUser;
    }

    @Override
    public void delete(Integer userId) {
        userRepository.deleteById(userId);
    }
}
