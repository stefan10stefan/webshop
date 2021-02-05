package com.service;

import com.config.SecurityUtils;
import com.model.User;
import com.model.dto.UserDTO;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO add(UserDTO userDTO) {


        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        user.setLat(userDTO.getLat());
        user.setLng(userDTO.getLng());
        user.setType(userDTO.getType());
        user.setDeleted(false);
        user.setType("USER");

        return new UserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO addManager(UserDTO userDTO) {


        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        user.setLat(userDTO.getLat());
        user.setLng(userDTO.getLng());
        user.setType(userDTO.getType());
        user.setDeleted(false);
        user.setType("MANAGER");

        return new UserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO edit(UserDTO userDTO) {

        User user = userRepository.getOne(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setLat(userDTO.getLat());
        user.setLng(userDTO.getLng());
        user.setType(userDTO.getType());

        return new UserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO changePassword(UserDTO userDTO) {

        User user = userRepository.getOne(getCurrentUser().getId());
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));

        return new UserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO get(Long id) {
        return new UserDTO(userRepository.getOne(id));
    }

    @Override
    public UserDTO getCurrentUser() {
        Optional<String> username = SecurityUtils.getCurrentUserLogin();

        Optional<User> user = userRepository.findOneByEmail(username.get());

        if(!user.isPresent()) {
            return null;
        }

        return new UserDTO(user.get());
    }

}
