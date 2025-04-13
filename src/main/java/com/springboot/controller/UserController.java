package com.springboot.controller;

import com.springboot.dto.AuthenticationRequest;
import com.springboot.dto.AuthenticationResponse;
import com.springboot.jwt.AuthUserServiceI;
import com.springboot.jwt.JwtUtils;
import com.springboot.model.User;
import com.springboot.repo.UserRepository;
import com.springboot.request.UserRequest;
import com.springboot.serviceI.UserServiceI;
import com.springboot.utils.APIResponse;
import com.springboot.utils.MessageUtilty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("user-API")
public class UserController {
    @Autowired
    private UserServiceI userServiceI;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthUserServiceI authUserServiceI;

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public APIResponse createUser(@RequestBody UserRequest userRequest) {
        try {
            APIResponse apiResponse = userServiceI.createUser(userRequest);
            System.out.println("apiResponse : " + apiResponse);
            return apiResponse;
        } catch (Exception e) {
            return new APIResponse(MessageUtilty.HttpStatus_INTERNAL_SERVER_ERROR, 500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));

            Optional<User> optionalUser = userRepository.findFirstByEmail(authenticationRequest.getEmail());

            if (optionalUser.isEmpty()) {
                throw new UsernameNotFoundException(MessageUtilty.USER_NOT_FOUND);
            } else {
                UserDetails userDetails = authUserServiceI.userDetails().loadUserByUsername(authenticationRequest.getEmail());
                String jwt = jwtUtils.generateToken(userDetails.getUsername());

                AuthenticationResponse response = new AuthenticationResponse();
                response.setJwt(jwt);
                response.setUserId(optionalUser.get().getId());
                response.setRole(optionalUser.get().getRole());
                return ResponseEntity.ok(new APIResponse(MessageUtilty.LOGIN_SUCCESSFULLY, 200, response));
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new APIResponse("Invalid credentials", 401, HttpStatus.UNAUTHORIZED));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse("Server error", 500, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}

