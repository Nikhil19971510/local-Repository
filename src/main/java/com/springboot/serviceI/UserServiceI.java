package com.springboot.serviceI;

import com.springboot.request.UserRequest;
import com.springboot.utils.APIResponse;

public interface UserServiceI {

    public APIResponse createUser(UserRequest userRequest) ;

}
