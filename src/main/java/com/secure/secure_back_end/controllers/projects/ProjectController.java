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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
public class ProjectController
{
    private final ProjectServiceImpl projectService;

    @Autowired
    public ProjectController(ProjectServiceImpl projectService)
    {
        this.projectService = projectService;
    }

    @GetMapping("/projects/{projectId}")
    @ApiOperation(value = "returns a single project", response = ProjectViewModel.class)
    public ProjectViewModel getProjectDetails(@PathVariable(value = "projectId") @Min(1) Long projectId)
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
    @ApiOperation(value = "returns a all  projects owned by the user with this Id")
    public ResponseEntity<List<ProjectViewModel>> getOwnProjects(@PathVariable(value = "userId") @Min(1) Long userId)
    {
        List<ProjectViewModel> allProjects = this.projectService.getOwnProjects(userId);
        return new ResponseEntity<>(allProjects, HttpStatus.OK);
    }

    @GetMapping("projects/assigned-developers/{projectId}")
    public List<UserViewModel> getAssignedPersonal(@PathVariable(value = "projectId") @Min(1) Long projectId)
    {
        return this.projectService.getAssignedDevelopers(projectId);
    }

    @PostMapping("/projects")
    public void createProject(@Valid @RequestBody ProjectCreateForm form)
    {
        this.projectService.createProject(form);
    }

    @PutMapping("/projects/{projectId}")
    public void editProject(@Valid @RequestBody ProjectEditForm form,
                            @PathVariable("projectId") @Min(1) Long projectId)
    {
        this.projectService.editProject(form, projectId);
    }

    @PutMapping("/projects/developers/{projectId}")
    public void assignDevelopers(@Valid @RequestBody ProjectChangeDevelopersForm form,
                                 @PathVariable("projectId") @Min(1) Long projectId,
                                 @RequestParam("action") String action)
    {
        System.out.println(action);
        System.out.println(projectId);

        this.projectService.assignDevelopers(form, projectId);
    }

}
