package com.trelloplus.service;

import com.trelloplus.model.Project;

import java.util.List;

public interface ProjectService {
    Project createProject(Project project, Long teamLeadId, Long createdById);

    List<Project> getAllProjects();
}
