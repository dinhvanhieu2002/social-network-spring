package com.example.socialnetwork.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.example.socialnetwork.config.ApplicationUtils.generateUUID;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    // change to UUID

    private String id = generateUUID();

    @NotEmpty
    @Size(min = 4, message = "Username atleast 4 character long!")
    private String name;

    @NotEmpty
    @Email(message = "Invalid Email address, Please Input valid Email!")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 6, message = "Input password within 3 to 6 Characters")
    private String password;

    @NotEmpty
    private String about;

    private Set<RoleDto> roles = new HashSet<>();
}

