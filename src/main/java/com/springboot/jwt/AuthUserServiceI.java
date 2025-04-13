package com.springboot.jwt;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthUserServiceI {
    UserDetailsService userDetails ();
}
