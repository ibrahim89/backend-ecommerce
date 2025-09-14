package com.techiemind.repo;

import com.techiemind.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    Optional<Object> findByEmail(String email);
}
