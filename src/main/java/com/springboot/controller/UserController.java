package com.springboot.controller;


import com.springboot.dto.*;
import com.springboot.jwt.*;
import com.springboot.utils.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;
import com.springboot.model.User;
import com.springboot.repo.UserRepository;
import com.springboot.request.UserRequest;
import com.springboot.serviceI.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
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
            return new APIResponse(MessageUtility.HttpStatus_INTERNAL_SERVER_ERROR, 500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));

            Optional<User> optionalUser = userRepository.findFirstByEmail(authenticationRequest.getEmail());

            if (optionalUser.isEmpty()) {
                throw new UsernameNotFoundException(MessageUtility.USER_NOT_FOUND);
            } else {
                UserDetails userDetails = authUserServiceI.userDetails()
                        .loadUserByUsername(authenticationRequest.getEmail());

                String jwt = jwtUtils.generateToken(userDetails.getUsername());

                AuthenticationResponse authResponse = AuthenticationResponse.builder()
                        .jwt(jwt).userId(optionalUser.get().getId()).role(optionalUser.get().getRole()).build();

                return ResponseEntity.ok(
                        new APIResponse(MessageUtility.LOGIN_SUCCESSFULLY, 200, authResponse)
                );
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new APIResponse("Invalid credentials", 401, HttpStatus.UNAUTHORIZED));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse("Server error", 500, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}

