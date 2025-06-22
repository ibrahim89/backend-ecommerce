package com.ecommerce.user_service.controller;

import com.ecommerce.user_service.dto.UserDto;
import com.ecommerce.user_service.http.HeaderGenerator;
import com.ecommerce.user_service.security.jwt.JwtProvider;
import com.ecommerce.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final ModelMapper modelMapper;

    private final UserService userService;
    private final HeaderGenerator headerGenerator;
    private final JwtProvider jwtProvider;

    public UserController(ModelMapper modelMapper, UserService userService, HeaderGenerator headerGenerator, JwtProvider jwtProvider) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.headerGenerator = headerGenerator;
        this.jwtProvider = jwtProvider;
    }


    @GetMapping("/hello")
    public String hello() {
        return "Hello from User Service";
    }
}
