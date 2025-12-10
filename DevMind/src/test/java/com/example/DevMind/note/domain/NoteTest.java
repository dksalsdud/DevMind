package com.example.DevMind.note.domain;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NoteTest {
    
    @Test
    @DisplayName("Note 객체 빌더 패턴 생성 테스트")
    void createNoteUsingBuilder() {

        // given
        String title = "Test Title";
        String content = "Test Content";
        SourceType sourceType = SourceType.INTERNAL;
        LocalDateTime now = LocalDateTime.now();

        // when
        Note note = Note.builder()
                .title(title)
                .content(content)
                .summary("Summary")
                .source_type(sourceType)
                .created_at(now)
                .build();

        // then
        assertThat(note.getTitle()).isEqualTo(title);
        assertThat(note.getContent()).isEqualTo(content);
        assertThat(note.getSource_type()).isEqualTo(sourceType);
        assertThat(note.getCreated_at()).isEqualTo(now);
    }

    @Test
    @DisplayName("Note 생성 시 view_count 기본값 0 확인")
    void checkDefaultViewCount() {
        
        // given
        Note note = Note.builder()
                .title("Title")
                .content("Content")
                .summary("Summary")
                .source_type(SourceType.EXTERNAL)
                .created_at(LocalDateTime.now())
                .build();

        // then
        assertThat(note.getView_count()).isEqualTo(0L);
    }
}
