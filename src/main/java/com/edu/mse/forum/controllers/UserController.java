package com.edu.mse.forum.controllers;

import com.edu.mse.forum.dtos.CreateUserDto;
import com.edu.mse.forum.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    public void createUser(@RequestBody CreateUserDto userDto) {
        // TODO: encrypt password
        userService.createUser(userDto);
    }
}
