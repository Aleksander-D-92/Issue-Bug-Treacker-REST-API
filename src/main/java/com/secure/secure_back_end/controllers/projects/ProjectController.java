package com.secure.secure_back_end.controllers.projects;

import com.secure.secure_back_end.dto.project.binding.ProjectChangeDevelopersForm;
import com.secure.secure_back_end.dto.project.binding.ProjectCreateForm;
import com.secure.secure_back_end.dto.project.binding.ProjectEditForm;
import com.secure.secure_back_end.dto.project.view.ProjectViewModel;
import com.secure.secure_back_end.dto.user.view.UserViewModel;
import com.secure.secure_back_end.services.interfaces.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collections;
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

    @GetMapping("/projects")
    @ApiOperation("action must equal \"all\", \"single\" or \"own\" or \"include-developer\" or \"include-qa\". If \"single\" or \"own\" you must pass a projectId or userId to specify. If  \"include-developer\" or \"include-qa\" specify userId Example: GET /projects?action=single&id=12")
    public List<ProjectViewModel> getProjects(@RequestParam(value = "action") @Pattern(regexp = "^all$|^single$|^own$|^include-developer$|^include-qa$") String action,
                                              @RequestParam(value = "id", required = false) @Min(1) Long id)
    {
        switch (action)
        {
            case "all":
                return this.projectService.findALl();
            case "single":
                return Collections.singletonList(this.projectService.findOne(id));
            case "own":
                return this.projectService.findByProjectManager(id);
            case "include-developer":
                return this.projectService.getProjectsThatIncludeDeveloper(id);
            case "include-qa":
                return this.projectService.getProjectsThatIncludeQA(id);
            default:
                return new ArrayList<>();
        }
    }

    @GetMapping("/projects/developers")
    @ApiOperation("action must equal \"assigned\" or \"available\". Returns assigned or available developers for a given project.\n Example GET /projects/developers?action=assigned&id=12")
    public List<UserViewModel> getDevelopers(@RequestParam(value = "action") @Pattern(regexp = "^assigned$|^available$") String action,
                                             @RequestParam(value = "id") @Min(1) Long projectId)
    {
        switch (action)
        {
            case "assigned":
            case "available":
                return this.projectService.getAvailableDevelopers(projectId);
            default:
                return new ArrayList<>();
        }
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
        switch (action)
        {
            case "assign":
                this.projectService.assignDevelopers(form, projectId);
                break;
            case "remove":
                this.projectService.removeDevelopers(form, projectId);
                break;
            default:
                break;
        }
    }
}
