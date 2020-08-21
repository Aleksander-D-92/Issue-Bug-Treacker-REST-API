package com.secure.secure_back_end.controllers.projects;

import com.secure.secure_back_end.dto.project.binding.ProjectChangeDevelopersForm;
import com.secure.secure_back_end.dto.project.binding.ProjectCreateEditForm;
import com.secure.secure_back_end.dto.project.view.ProjectViewModel;
import com.secure.secure_back_end.services.implementations.ProjectEditForm;
import com.secure.secure_back_end.services.implementations.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProjectController
{
    private final ProjectServiceImpl projectService;

    @Autowired
    public ProjectController(ProjectServiceImpl projectService)
    {
        this.projectService = projectService;
    }

    @GetMapping("/projects/get-all-projects")
    public ResponseEntity<List<ProjectViewModel>> getAllProjects()
    {
        List<ProjectViewModel> allProjects = this.projectService.getAllProjects();
        return new ResponseEntity<>(allProjects, HttpStatus.OK);
    }

    @GetMapping("/projects/get-own-projects/{userId}")
    public ResponseEntity<List<ProjectViewModel>> getOwnProjects(@PathVariable(value = "userId") long userId)
    {
        List<ProjectViewModel> allProjects = this.projectService.getOwnProjects(userId);
        return new ResponseEntity<>(allProjects, HttpStatus.OK);
    }

    @GetMapping("/projects/get-project-description/{projectId}")
    public ProjectViewModel getProjectDetails(@PathVariable(value = "projectId") long projectId)
    {
        return this.projectService.getProjectDescription(projectId);
    }

    @PostMapping("/projects/create-project")
    public void createProject(@Valid @RequestBody ProjectCreateEditForm projectCreateEditForm)
    {
        this.projectService.createProject(projectCreateEditForm);
    }

    @PutMapping("/projects/edit-project")
    public void editProject(@Valid @RequestBody ProjectEditForm projectEditForm)
    {
        this.projectService.editProject(projectEditForm);
    }

    @PutMapping("/projects/assign-developers-to-project")
    public void assignDevelopers(@Valid @RequestBody ProjectChangeDevelopersForm form)
    {
        this.projectService.assignDevelopers(form);
    }

    @PutMapping("/projects/remove-developers-from-project")
    public void removeDevelopers(@Valid @RequestBody ProjectChangeDevelopersForm form)
    {
        this.projectService.removeDevelopers(form);
    }
}
