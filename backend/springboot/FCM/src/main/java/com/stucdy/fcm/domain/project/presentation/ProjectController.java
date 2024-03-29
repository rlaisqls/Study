package com.stucdy.fcm.domain.project.presentation;

import com.stucdy.fcm.domain.project.presentation.dto.request.CreateProjectRequest;
import com.stucdy.fcm.domain.project.presentation.dto.request.LeaveProjectRequest;
import com.stucdy.fcm.domain.project.service.CreateProjectService;
import com.stucdy.fcm.domain.project.service.LeaveProjectService;
import com.stucdy.fcm.domain.project.service.QueryProjectInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/projects")
@RestController
public class ProjectController {

    private final CreateProjectService createProjectService;
    private final LeaveProjectService leaveProjectService;
    private final QueryProjectInfoService queryProjectInfoService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void createProject(@RequestBody @Valid CreateProjectRequest request) {
        createProjectService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/leave")
    public void leaveProject(@RequestBody @Valid LeaveProjectRequest request) {
        leaveProjectService.execute(request);
    }

}