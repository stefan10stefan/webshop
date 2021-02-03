package com.service;

import com.model.User;
import com.model.dto.UserDTO;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User add(UserDTO user) {
        return null;
    }

    @Override
    public User edit(UserDTO user) {
        return null;
    }

    @Override
    public User get(Long id) {
        return userRepository.getOne(id);
    }
}
