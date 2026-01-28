package com.pengoo.brainTracker.controller;

import com.pengoo.brainTracker.dto.CreateSessionRequest;
import com.pengoo.brainTracker.dto.StudySessionResponse;
import com.pengoo.brainTracker.dto.XpResponse;
import com.pengoo.brainTracker.model.entity.StudySession;
import com.pengoo.brainTracker.model.service.StudyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/study")
public class StudyController {

    private final StudyService studyService;

    public StudyController(StudyService studyService){
        this.studyService = studyService;
    }

    @PostMapping
    public XpResponse createSession(@RequestBody CreateSessionRequest request){
        return studyService.addSession(request);
    }

    @GetMapping
    public List<StudySessionResponse> getAllSessions(){
        return studyService.getAllSessions();
    }


}
