package com.controller;

import com.model.dto.UserDTO;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path="/{id}")
    public UserDTO get(@PathVariable("id") Long id) {
        return userService.get(id);
    }

    @PostMapping(path="/")
    public UserDTO add(@RequestBody UserDTO user) {
        return  userService.add(user);
    }

    @PutMapping(path="/")
    public UserDTO edit(@RequestBody UserDTO user) {
        return userService.edit(user);
    }

    @GetMapping(path="/current")
    public UserDTO getCurrent() {
        return userService.getCurrentUser();
    }
}
