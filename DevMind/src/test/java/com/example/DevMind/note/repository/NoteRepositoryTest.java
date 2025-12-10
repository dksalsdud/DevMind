package com.example.DevMind.note.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.example.DevMind.note.domain.Note;
import com.example.DevMind.note.domain.SourceType;


import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 DB 사용
public class NoteRepositoryTest {
    
    @Autowired
    private NoteRepository noteRepository;

    @Test
    @DisplayName("Note 저장 및 조회 테스트")
    void saveAndFindNote() {
        // given
        Note note = Note.builder()
                .title("Spring Boot Testing")
                .content("It is very important.")
                .summary("Testing Summary")
                .original_url("http://example.com")
                .platform("Blog")
                .source_type(SourceType.INTERNAL)
                .created_at(LocalDateTime.now())
                .build();

        // when
        Note savedNote = noteRepository.save(note);
        
        // then
        assertThat(savedNote.getId()).isNotNull(); // ID 자동 생성 확인
        assertThat(savedNote.getTitle()).isEqualTo(note.getTitle());
        assertThat(savedNote.getContent()).isEqualTo(note.getContent());
        assertThat(savedNote.getView_count()).isEqualTo(0); // 기본값 확인

        // findById로 재조회
        Optional<Note> foundNote = noteRepository.findById(savedNote.getId());
        assertThat(foundNote).isPresent();
        assertThat(foundNote.get().getTitle()).isEqualTo("Spring Boot Testing");
    }
}
