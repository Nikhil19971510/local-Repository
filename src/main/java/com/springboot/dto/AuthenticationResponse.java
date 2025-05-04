package com.springboot.dto;

import com.springboot.enums.UserRole;
import lombok.Builder;



@SuppressWarnings("All")
public class AuthenticationResponse {
    private int userId;
    private String jwt;
    private UserRole role;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
