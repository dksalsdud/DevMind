package com.example.DevMind.user.reository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DevMind.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<Long, User> {
    
}
