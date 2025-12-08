package com.example.DevMind.user.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.example.DevMind.user.domain.User;
import com.example.DevMind.user.domain.UserRole;
import com.example.DevMind.user.reository.UserRepository;

@DataJpaTest
public class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("User 저장 및 조회 테스트")
    void save_and_find_user() {
        
        // given
        User user = User.builder()
                .email("save@test.com")
                .nickname("SaveNick")
                .name("SaveName")
                .social_provider("NAVER")
                .created_at(LocalDateTime.now())
                .role(UserRole.ROLE_USER)
                .build();

        // when
        User savedUser = userRepository.save(user);

        // then
        assertNotNull(savedUser.getId()); // ID 자동 생성 확인
        assertEquals("save@test.com", savedUser.getEmail()); // 이메일 맞는지 확인
        
        // ID로 다시 조회 확인
        User foundUser = userRepository.findById(savedUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        assertEquals("SaveNick", foundUser.getNickname());
    }
}
