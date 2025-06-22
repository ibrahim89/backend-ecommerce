package com.ecommerce.user_service.service.impl;

import com.ecommerce.user_service.dto.UserDto;
import com.ecommerce.user_service.dto.request.ChangePasswordRequest;
import com.ecommerce.user_service.dto.request.Login;
import com.ecommerce.user_service.dto.request.SignUp;
import com.ecommerce.user_service.dto.response.JwtResponseMessage;
import com.ecommerce.user_service.entity.RoleName;
import com.ecommerce.user_service.entity.User;
import com.ecommerce.user_service.exception.custom.EmailOrUsernameNotFoundException;
import com.ecommerce.user_service.exception.custom.PhoneNumberNotFoundException;
import com.ecommerce.user_service.repository.UserRepository;
import com.ecommerce.user_service.security.jwt.JwtProvider;
import com.ecommerce.user_service.security.userprinciple.UserDetailService;
import com.ecommerce.user_service.service.RoleService;
import com.ecommerce.user_service.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public  class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserDetailService userDetailsService;
    private final ModelMapper modelMapper;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, UserDetailService userDetailsService, ModelMapper modelMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }


    @Override
    public Mono<User> register(SignUp signUp) {
        return Mono.defer(() -> {
            if (existsByUsername(signUp.getUsername())) {
                return Mono.error(new EmailOrUsernameNotFoundException("The username " + signUp.getUsername() + " is existed, please try again."));
            }
            if (existsByEmail(signUp.getEmail())) {
                return Mono.error(new EmailOrUsernameNotFoundException("The email " + signUp.getEmail() + " is existed, please try again."));
            }
            if (existsByPhoneNumber(signUp.getPhone())) {
                return Mono.error(new PhoneNumberNotFoundException("The phone number " + signUp.getPhone() + " is existed, please try again."));
            }

            User user = modelMapper.map(signUp, User.class);
            user.setPasswordHash(passwordEncoder.encode(signUp.getPassword()));
            user.setRoles(signUp.getRoles()
                    .stream()
                    .map(role -> roleService.findByName(mapToRoleName(role))
                            .orElseThrow(() -> new RuntimeException("Role not found in the database.")))
                    .collect(Collectors.toSet()));

            userRepository.save(user);
            return Mono.just(user);
        });
    }

    private RoleName mapToRoleName(String roleName) {
        return switch (roleName) {
            case "ADMIN", "admin", "Admin" -> RoleName.ADMIN;
            case "USER", "user", "User" -> RoleName.USER;
            default -> null;
        };
    }
    @Override
    public Mono<JwtResponseMessage> login(Login signInForm) {
        return null;
    }

    @Override
    public Mono<Void> logout() {
        return null;
    }

    @Override
    public Mono<User> update(Long userId, SignUp update) {
        return null;
    }

    @Override
    public Mono<String> changePassword(ChangePasswordRequest request) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return "";
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String userName) {
        return Optional.empty();
    }

    @Override
    public Page<UserDto> findAllUsers(int page, int size, String sortBy, String sortOrder) {
        return null;
    }
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByPhoneNumber(String phone) {
        return userRepository.existsByPhoneNumber(phone);
    }
}