package com.pengoo.brainTracker.model.service;

import com.pengoo.brainTracker.dto.CreateSessionRequest;
import com.pengoo.brainTracker.dto.StudySessionResponse;
import com.pengoo.brainTracker.dto.TotalXpResponse;
import com.pengoo.brainTracker.dto.XpResponse;
import com.pengoo.brainTracker.model.entity.StudySession;
import com.pengoo.brainTracker.repository.SessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {
    //Using AssertJ

    @Mock
    private SessionRepository repository;

    @InjectMocks
    private StudyService service;

    @Test
    void testAddSession(){

        CreateSessionRequest request =
                new CreateSessionRequest(60);

        StudySession fakeSaved =
                new StudySession(LocalDate.now(), 60, 6);

        when(repository.save(any())).thenReturn(fakeSaved);

        //When
        XpResponse response = service.addSession(request);

        //Then
        assertThat(response.getXpEarned()).isEqualTo(6);
        assertThat(response.getTotalXp()).isEqualTo(6);

        verify(repository).save(any(StudySession.class));
    }

    @Test
    void shouldGetAllSessions(){
        //given
        StudySession session = new StudySession(LocalDate.now(), 60, 6);
        StudySession session1 = new StudySession(LocalDate.now(), 70, 7);
        StudySession session2 = new StudySession(LocalDate.now(), 80, 8);

        List<StudySession> expected = List.of(session, session1, session2);

        when(repository.findAll()).thenReturn(expected);

        //when
        List<StudySessionResponse> responses = service.getAllSessions();

        //then
        assertThat(responses).hasSize(3);

        assertThat(responses.get(0).getMinuteStudied()).isEqualTo(60);
        assertThat(responses.get(0).getXpEarned()).isEqualTo(6);

        assertThat(responses.get(1).getMinuteStudied()).isEqualTo(70);
        assertThat(responses.get(1).getXpEarned()).isEqualTo(7);

        assertThat(responses.get(2).getMinuteStudied()).isEqualTo(80);
        assertThat(responses.get(2).getXpEarned()).isEqualTo(8);

        verify(repository).findAll();
    }

    @Test
    void shouldReturnTotalXp(){
        //given
        StudySession session = new StudySession(LocalDate.now(), 60, 6);
        StudySession session1 = new StudySession(LocalDate.now(), 70, 7);
        StudySession session2 = new StudySession(LocalDate.now(), 80, 8);

        List<StudySession> expected = List.of(session, session1, session2);

        when(repository.findAll()).thenReturn(expected);

        //when
        TotalXpResponse xpResponse = service.getTotalXp();

        //then

        assertThat(xpResponse.totalXp()).isEqualTo(6+7+8);
        assertThat(xpResponse.sessionsCount()).isEqualTo(3);
    }

    @Test
    void shouldReturnStreakBonusAndTotalWithBonus(){
        LocalDate today = LocalDate.of(2026, 2, 12);
        LocalDate yesterday = LocalDate.of(2026, 2, 11);
        LocalDate twoDaysAgo = LocalDate.of(2026, 2, 10);

        StudySession session = new StudySession(today, 60, 6);
        StudySession session1 = new StudySession(yesterday, 70, 7);
        StudySession session2 = new StudySession(twoDaysAgo, 80, 8);


    }


}
