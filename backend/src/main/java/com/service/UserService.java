package com.service;

import com.model.User;
import com.model.dto.UserDTO;

public interface UserService {

    UserDTO add(UserDTO user);
    UserDTO addManager(UserDTO user);
    UserDTO edit(UserDTO user);
    UserDTO changePassword(UserDTO user);
    UserDTO get(Long id);
    UserDTO getCurrentUser();
}
