package com.service;

import com.model.User;
import com.model.dto.UserDTO;

public interface UserService {

    public User add(UserDTO user);
    public User edit(UserDTO user);
    public User get(Long id);
}
