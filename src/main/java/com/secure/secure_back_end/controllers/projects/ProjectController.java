package com.secure.secure_back_end.controllers.projects;

import com.secure.secure_back_end.dto.project.ProjectChangeDevelopersForm;
import com.secure.secure_back_end.dto.project.ProjectCreateForm;
import com.secure.secure_back_end.dto.project.ProjectEditForm;
import com.secure.secure_back_end.dto.project.ProjectTableModel;
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
    public ResponseEntity<List<ProjectTableModel>> getAllProjects()
    {
        List<ProjectTableModel> allProjects = this.projectService.getAllProjects();
        return new ResponseEntity<>(allProjects, HttpStatus.OK);
    }

    @GetMapping("/projects/get-own-projects/{userId}")
    public ResponseEntity<List<ProjectTableModel>> getOwnProjects(@PathVariable(value = "userId") long userId)
    {
        List<ProjectTableModel> allProjects = this.projectService.getOwnProjects(userId);
        return new ResponseEntity<>(allProjects, HttpStatus.OK);
    }

    @GetMapping("/projects/get-project-details/{projectId}")
    public String getProjectDetails(@PathVariable(value = "projectId") long projectId)
    {
        this.projectService.getProjectDetailsById(projectId);
        return "";
    }

    @PostMapping("/projects/create-project")
    public void createProject(@Valid @RequestBody ProjectCreateForm projectCreateForm)
    {
        this.projectService.createProject(projectCreateForm);
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
