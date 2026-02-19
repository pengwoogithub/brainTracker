package com.pengoo.brainTracker.repository;


import com.pengoo.brainTracker.model.entity.StudySession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
class SessionRepositoryTest {
    //Using JUnit only

    @Autowired
    private SessionRepository repository;

    @Test
    void saveAndRetrieveFunctionTest(){
        //given
        StudySession session = new StudySession("Task1", LocalDate.now(), 50, 200);

        //when
        StudySession saved = repository.save(session);

        Assertions.assertNotNull(saved.getId());

        Optional<StudySession> found = repository.findById(saved.getId());

        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals(50, found.get().getMinutesStudied());
        Assertions.assertEquals(200, found.get().getXpEarned());

    }

}
