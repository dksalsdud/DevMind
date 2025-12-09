package com.example.DevMind.note.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DevMind.note.domain.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    
}
