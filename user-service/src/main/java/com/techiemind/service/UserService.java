package com.techiemind.service;

import com.techiemind.entity.User;
import com.techiemind.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public User createOrUpdateUser(User user){
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
    public Optional<User> getUserById(Long userId){
        return userRepo.findById(userId);
    }
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
    public void deleteUser(Long userId){
        userRepo.deleteById(userId);
    }

}
