package com.secure.secure_back_end.controllers.projects;

import com.secure.secure_back_end.dto.project.binding.ProjectChangeDevelopersForm;
import com.secure.secure_back_end.dto.project.binding.ProjectCreateForm;
import com.secure.secure_back_end.dto.project.binding.ProjectEditForm;
import com.secure.secure_back_end.dto.project.view.ProjectViewModel;
import com.secure.secure_back_end.dto.user.view.UserViewModel;
import com.secure.secure_back_end.services.implementations.ProjectServiceImpl;
import io.swagger.annotations.ApiOperation;
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
        this.   projectService = projectService;
    }

    @GetMapping("/projects/{projectId}")
    @ApiOperation(value = "returns a single project", response = ProjectViewModel.class)
    public ProjectViewModel getProjectDetails(@PathVariable(value = "projectId") long projectId)
    {
        return this.projectService.getProject(projectId);
    }

    @GetMapping("/projects/all")
    @ApiOperation(value = "returns a all  projects", response = ProjectViewModel[].class)
    public ResponseEntity<List<ProjectViewModel>> getAllProjects()
    {
        List<ProjectViewModel> allProjects = this.projectService.getAllProjects();
        return new ResponseEntity<>(allProjects, HttpStatus.OK);
    }

    @GetMapping("/projects/own/{userId}")
    @ApiOperation(value = "returns a all  projects owned by the user with this Id", response = ProjectViewModel[].class)
    public ResponseEntity<List<ProjectViewModel>> getOwnProjects(@PathVariable(value = "userId") long userId)
    {
        List<ProjectViewModel> allProjects = this.projectService.getOwnProjects(userId);
        return new ResponseEntity<>(allProjects, HttpStatus.OK);
    }

    @GetMapping("projects/{projectId}/assigned-developers")
    public List<UserViewModel> getAssignedPersonal(@PathVariable(value = "projectId") long projectId)
    {
        return this.projectService.getAssignedDevelopers(projectId);
    }

    @PostMapping("/projects")
    public void createProject(@Valid @RequestBody ProjectCreateForm form)
    {
        this.projectService.createProject(form);
    }

    @PutMapping("/projects/{projectId}")
    public void editProject(@Valid @RequestBody ProjectEditForm form, @PathVariable("projectId") long projectId)
    {
        this.projectService.editProject(form, projectId);
    }

    @PutMapping("/projects/developers/assign")
    public void assignDevelopers(@Valid @RequestBody ProjectChangeDevelopersForm form)
    {
        this.projectService.assignDevelopers(form);
    }

    @PutMapping("/projects/developers/remove")
    public void removeDevelopers(@Valid @RequestBody ProjectChangeDevelopersForm form)
    {
        this.projectService.removeDevelopers(form);
    }
}
