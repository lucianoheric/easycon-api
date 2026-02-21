package com.tribosoftec.easycon_api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtService jwtService;

    public String login(String email) {
        return jwtService.generateToken(email);
    }

}
