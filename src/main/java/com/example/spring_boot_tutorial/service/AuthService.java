package com.example.spring_boot_tutorial.service;

import com.example.spring_boot_tutorial.payload.LoginDTO;
import com.example.spring_boot_tutorial.payload.RegisterDTO;

public interface AuthService {

    String login(LoginDTO loginDTO);

    String register(RegisterDTO registerDTO);

}
