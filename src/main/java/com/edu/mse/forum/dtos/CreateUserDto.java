package com.edu.mse.forum.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateUserDto {

    private String email;
    private String role;
    private String name;
    private String password;
    private Date dateOfBirth;
}
