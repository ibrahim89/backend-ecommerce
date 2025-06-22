package com.ecommerce.user_service.service;

import com.ecommerce.user_service.dto.UserDto;
import com.ecommerce.user_service.dto.request.ChangePasswordRequest;
import com.ecommerce.user_service.dto.request.Login;
import com.ecommerce.user_service.dto.request.SignUp;
import com.ecommerce.user_service.dto.response.JwtResponseMessage;
import com.ecommerce.user_service.entity.User;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Mono<User> register(SignUp signUp);
    Mono<JwtResponseMessage> login(Login signInForm);
    Mono<Void> logout();
    Mono<User> update(Long userId, SignUp update);
    Mono<String> changePassword(ChangePasswordRequest request);
    String delete(Long id);
    Optional<User> findById(Long userId);
    Optional<User> findByUsername(String userName);
    Page<UserDto> findAllUsers(int page, int size, String sortBy, String sortOrder);
}
