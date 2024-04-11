package com.example.socialnetwork.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.exceptions.ResourceNotFoundException;
import com.example.socialnetwork.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;


    //UserDetailsService methods
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        //load Username of a user from db

        User user = this.userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User ", " email: "+username, 0));

        //this 'loadUserByUsername' method return type is UserDetails,
        //so we need to convert 'user' to 'UserDetails' through implements 'UserDetails' in 'User' Entity Class.
        return user;
    }

}