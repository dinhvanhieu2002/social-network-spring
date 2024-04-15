package com.example.socialnetwork.services.impl;

import com.example.socialnetwork.config.ApplicationConstants;
import com.example.socialnetwork.entity.Role;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.payloads.UserDto;
import com.example.socialnetwork.repository.RoleRepository;
import com.example.socialnetwork.repository.UserRepository;
import com.example.socialnetwork.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Role role = this.roleRepository.findById(ApplicationConstants.NORMAL_USER_ID_HARDCODED_ID_VALUE);
        user.getRoles().add(role);

        User newUser = this.userRepository.save(user);
//        this.userRole

        return this.modelMapper.map(newUser, UserDto.class);
    }
}
