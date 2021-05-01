package com.example.jpa.controller;


import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Project;
import com.example.jpa.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository ;

    @GetMapping("/project/view")
    public List<Project> getAllProject(Project project) {
        return projectRepository.findAll();
    }

    @PostMapping("/project/add")
    public Project createProject(@Valid @RequestBody Project project) {
        return projectRepository.save(project);
    }

    @PutMapping("/project/{projectId}")
    public Project updateProject(@PathVariable Long projectId, @Valid @RequestBody Project projectRequest) {
        return projectRepository.findById(projectId).map(project -> {
            project.setProject_name(projectRequest.getProject_name());
            project.setProject_manager(projectRequest.getProject_manager());
            return projectRepository.save(project);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + projectId + " not found"));
    }


    @DeleteMapping("/project/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId) {
        return projectRepository.findById(projectId).map(project -> {
            projectRepository.delete(project);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + projectId + " not found"));
    }

}
