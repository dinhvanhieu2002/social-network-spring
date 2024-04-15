package com.example.socialnetwork.services;

import com.example.socialnetwork.payloads.UserDto;

public interface UserService {
    UserDto registerNewUser(UserDto user);
}
