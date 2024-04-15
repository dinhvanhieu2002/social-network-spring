package com.example.socialnetwork.controller;

import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.exceptions.ApiExceptionHandler;
import com.example.socialnetwork.payloads.JwtAuthenticationRequest;
import com.example.socialnetwork.payloads.JwtAuthenticationResponse;
import com.example.socialnetwork.payloads.UserDto;
import com.example.socialnetwork.repository.UserRepository;
import com.example.socialnetwork.security.JwtTokenHelper;
import com.example.socialnetwork.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> createToken(@Valid @RequestBody JwtAuthenticationRequest request) throws Exception {
        this.authenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String ourGeneratedToken = this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(ourGeneratedToken);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        System.out.println(usernamePasswordAuthenticationToken+"Wasim...");
        try {
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException e) {
            System.out.println("Invalid Username or Password!");
            throw new ApiExceptionHandler("Invalid Username or Password!");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<UserDto> registerNewUser(@Valid @RequestBody UserDto userDto) {
        UserDto registeredNewUser = this.userService.registerNewUser(userDto);

        return new ResponseEntity<>(registeredNewUser, HttpStatus.CREATED);
    }
}
