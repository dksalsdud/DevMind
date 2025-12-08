package com.example.DevMind.user.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.dao.DataIntegrityViolationException;

import com.example.DevMind.user.domain.User;
import com.example.DevMind.user.domain.UserRole;
import com.example.DevMind.user.reository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 DB 사용
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

    @Test
    @DisplayName("User 목록 조회 테스트")
    void find_all_users() {

        // given
        User user1 = User.builder()
                .email("user1@test.com")
                .nickname("Nick1")
                .name("Name1")
                .social_provider("KAKAO")
                .created_at(LocalDateTime.now())
                .role(UserRole.ROLE_USER)
                .build();

        User user2 = User.builder()
                .email("user2@test.com")
                .nickname("Nick2")
                .name("Name2")
                .social_provider("GOOGLE")
                .created_at(LocalDateTime.now())
                .role(UserRole.ROLE_ADMIN)
                .build();

        userRepository.save(user1);
        userRepository.save(user2);

        // when
        List<User> userList = userRepository.findAll();

        // then
        assertEquals(2, userList.size());
    }

    @Test
    @DisplayName("User 삭제 테스트")
    void delete_user() {

        // given
        User user = User.builder()
                .email("delete@test.com")
                .nickname("DeleteNick")
                .name("DeleteName")
                .social_provider("GOOGLE")
                .created_at(LocalDateTime.now())
                .role(UserRole.ROLE_GUEST)
                .build();

        User savedUser = userRepository.save(user);

        // when
        userRepository.delete(savedUser);

        // then
        Optional<User> deletedUser = userRepository.findById(savedUser.getId());
        assertTrue(deletedUser.isEmpty());
    }
    
    @Test
    @DisplayName("Email 중복 저장 시 예외 발생 테스트 (Unique 제약조건)")
    void duplicate_email_exception() {
        
        // given
        User user1 = User.builder()
                .email("duplicate@test.com")
                .nickname("User1")
                .name("Name1")
                .social_provider("GOOGLE")
                .created_at(LocalDateTime.now())
                .role(UserRole.ROLE_USER)
                .build();

        User user2 = User.builder()
                .email("duplicate@test.com") // 동일한 이메일
                .nickname("User2")
                .name("Name2")
                .social_provider("KAKAO")
                .created_at(LocalDateTime.now())
                .role(UserRole.ROLE_USER)
                .build();

        userRepository.save(user1);

        // when & then
        // assertThrows(예상되는 예외 클래스, 실행할 코드)
        assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.save(user2);
        });
    }

}
