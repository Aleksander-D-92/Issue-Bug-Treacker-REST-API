package com.secure.secure_back_end.controllers.projects;

import com.secure.secure_back_end.dto.project.binding.ProjectChangeDevelopersForm;
import com.secure.secure_back_end.dto.project.binding.ProjectCreateForm;
import com.secure.secure_back_end.dto.project.binding.ProjectEditForm;
import com.secure.secure_back_end.dto.project.view.ProjectViewModel;
import com.secure.secure_back_end.dto.user.view.UserViewModel;
import com.secure.secure_back_end.services.interfaces.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@Validated
public class ProjectController
{
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService)
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

    @GetMapping("projects/developers/{projectId}")
    @ApiOperation(value = "returns the assigned developers for this project")
    public List<UserViewModel> getAssignedPersonal(@PathVariable(value = "projectId") @Min(1) Long projectId)
    {
        return this.projectService.getAssignedDevelopers(projectId);
    }

    @GetMapping("projects/developers-available/{projectId}")
    @ApiOperation(value = "returns the developers you can assign to this project")
    public List<UserViewModel> getNotAssignedDevelopers(@PathVariable(value = "projectId") @Min(1) Long projectId)
    {
        return this.projectService.getAvailableDevelopers(projectId);
    }

    @PostMapping("/projects/{userId}")
    @ApiOperation(value = "create a new project")
    public void createProject(@Valid @RequestBody ProjectCreateForm form, @PathVariable("userId") @Min(1) Long userId)
    {
        this.projectService.createProject(form, userId);
    }

    @PutMapping("/projects/{projectId}")
    @ApiOperation(value = "edit an existing project")
    public void editProject(@Valid @RequestBody ProjectEditForm form,
                            @PathVariable("projectId") @Min(1) Long projectId)
    {
        this.projectService.editProject(form, projectId);
    }

    @PutMapping("/projects/developers/{projectId}")
    @ApiOperation(value = "assign on remove developers based on the @RequestParam(\"action\") if it's assign or remove")
    public void assignDevelopers(@Valid @RequestBody ProjectChangeDevelopersForm form,
                                 @PathVariable("projectId") @Min(1) Long projectId,
                                 @RequestParam("action") @Pattern(regexp = "^assign$|^remove$") String action)
    {
        if (action.toLowerCase().equals("assign"))
        {
            this.projectService.assignDevelopers(form, projectId);
        } else if (action.toLowerCase().equals("remove"))
        {
            this.projectService.removeDevelopers(form, projectId);
        }
    }

}
