package com.example.DevMind.user.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {
    
    @Test
    @DisplayName("User 객체 생성 및 빌더 패턴 테스트")
    void create_user_test() {
        
        // given
        String email = "test@example.com";
        String nickname = "Tester";
        String name = "Test User";
        String socialProvider = "GOOGLE";
        UserRole role = UserRole.ROLE_USER;
        LocalDateTime now = LocalDateTime.now();

        // when
        User user = User.builder()
                .email(email)
                .nickname(nickname)
                .name(name)
                .social_provider(socialProvider)
                .role(role)
                .created_at(now)
                .build();

        // then: assertEquals(기대값, 실제값) 순서
        assertEquals(email, user.getEmail());
        assertEquals(nickname, user.getNickname());
        assertEquals(name, user.getName());
        assertEquals(socialProvider, user.getSocial_provider());
        assertEquals(role, user.getRole());
        assertEquals(now, user.getCreated_at());
    }

}
