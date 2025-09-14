package com.techiemind.controller;

import com.techiemind.entity.User;
import com.techiemind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User saveUser(@RequestBody User user){
        return userService.createOrUpdateUser(user);
    }
    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId){
        Optional<User> user = userService.getUserById(userId);
        return user.get();

    }
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) throws Exception {
        try{
            userService.deleteUser(userId);
            return "User: "+userId+" deleted Successfully";
        }catch (Exception e){
            throw new Exception("Unable to delete the user", e);
        }

    }
}
