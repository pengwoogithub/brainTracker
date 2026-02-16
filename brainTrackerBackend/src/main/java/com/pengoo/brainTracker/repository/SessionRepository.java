package com.pengoo.brainTracker.repository;

import com.pengoo.brainTracker.model.entity.StudySession;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface SessionRepository extends JpaRepository<StudySession, Long> {
}
