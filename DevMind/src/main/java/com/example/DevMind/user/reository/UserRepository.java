package com.example.DevMind.user.reository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DevMind.user.domain.User;

public interface UserRepository extends JpaRepository<Long, User> {
    
}
