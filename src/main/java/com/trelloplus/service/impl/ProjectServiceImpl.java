package com.trelloplus.service.impl;

import com.trelloplus.exception.UserNotFoundException;
import com.trelloplus.model.Project;
import com.trelloplus.model.User;
import com.trelloplus.repository.ProjectRepository;
import com.trelloplus.repository.UserRepository;
import com.trelloplus.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Project createProject(Project project, Long teamLeadId, Long createdById) {
        User teamLead = userRepository.findById(teamLeadId).orElseThrow(() -> new UserNotFoundException(teamLeadId));
        User createdBy = userRepository.findById(createdById).orElseThrow(() -> new UserNotFoundException(createdById));

        project.setTeamLead(teamLead);
        project.setCreatedBy(createdBy);
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
