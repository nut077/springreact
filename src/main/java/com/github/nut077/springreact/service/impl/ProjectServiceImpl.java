package com.github.nut077.springreact.service.impl;

import com.github.nut077.springreact.entity.Project;
import com.github.nut077.springreact.exception.ProjectIdException;
import com.github.nut077.springreact.repository.ProjectRepository;
import com.github.nut077.springreact.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {
    private ProjectRepository projectRepository;

    @Override
    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project Id '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    @Override
    public Project findByProjectIdentifier(String projectIdentifier) {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Project Id '" + projectIdentifier.toUpperCase() + "' does not exists");
        }
        return project;
    }

    @Override
    public Iterable<Project> findAllProject() {
        return projectRepository.findAll();
    }

    @Override
    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }
}
