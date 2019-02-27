package com.github.nut077.springreact.controller;

import com.github.nut077.springreact.entity.Project;
import com.github.nut077.springreact.service.MapValidationErrorService;
import com.github.nut077.springreact.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
@AllArgsConstructor
public class ProjectController {
    private ProjectService projectService;
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<?> mapError = mapValidationErrorService.getError(result);
        if (mapError != null) {
            return mapError;
        }
        Project projectSave = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(projectSave, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
        Project project = projectService.findByProjectIdentifier(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> findAllProject() {
        return projectService.findAllProject();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProjectById(@PathVariable String projectId) {
        Project project = projectService.findByProjectIdentifier(projectId);
        projectService.deleteProject(project);
        return new ResponseEntity<>("Project with id : '" + projectId + "' was deleted", HttpStatus.OK);
    }
}
