package com.springboot.serviceImpl;

import com.springboot.enums.UserRole;
import com.springboot.model.User;
import com.springboot.repo.UserRepository;
import com.springboot.request.UserRequest;
import com.springboot.serviceI.UserServiceI;
import com.springboot.utils.APIResponse;
import com.springboot.utils.MessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceI {

    @Autowired
    private UserRepository userRepository;

    @Override
    public APIResponse createUser(UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findFirstByEmail(userRequest.getEmail());
        if (optionalUser.isPresent()) {
            return new APIResponse(MessageUtility.USER_ALREADY_CREATED, 500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (userRequest.getUserName() != null || userRequest.getEmail() != null || userRequest.getPassword() != null || userRequest.getUserRole() != null) {
            User user = new User();
            user.setEmail(userRequest.getEmail());
            user.setUserName(userRequest.getUserName());
            user.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
            user.setRole(userRequest.getUserRole().equals("1") ? UserRole.CUSTOMER : UserRole.ADMIN);
            User userSave = userRepository.save(user);
            return new APIResponse(MessageUtility.USER_CREATE_SUCCESSFULLY, 200, userSave);
        } else {
            return new APIResponse(MessageUtility.USER_NOT_VALID_REQUEST, 500, HttpStatus.BAD_REQUEST);
        }

    }



}
