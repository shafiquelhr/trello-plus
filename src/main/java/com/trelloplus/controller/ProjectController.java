package com.trelloplus.controller;

import com.trelloplus.dto.ProjectDto;
import com.trelloplus.mapper.ProjectMapper;
import com.trelloplus.model.Project;
import com.trelloplus.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    public ProjectController(ProjectService projectService, ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    // POST a project
    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto dto) {
        Project entity = projectMapper.toEntity(dto);
        Project saved = projectService.createProject(entity, dto.getTeamLeadId(), dto.getCreatedById());
        return new ResponseEntity<>(projectMapper.toDto(saved), HttpStatus.CREATED);
    }

    // GET all projects
    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        List<ProjectDto> dtos = projects.stream().map(projectMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
