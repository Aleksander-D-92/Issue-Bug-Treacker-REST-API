package com.secure.secure_back_end.services.interfaces;

import com.secure.secure_back_end.dto.project.binding.ProjectChangeDevelopersForm;
import com.secure.secure_back_end.dto.project.binding.ProjectCreateForm;
import com.secure.secure_back_end.dto.project.binding.ProjectEditForm;
import com.secure.secure_back_end.dto.project.view.ProjectViewModel;
import com.secure.secure_back_end.dto.user.view.UserViewModel;

import java.util.List;

public interface ProjectService
{
    void createProject(ProjectCreateForm projectCreateForm, Long userId);

    void editProject(ProjectEditForm projectEditForm, long projectId);

    ProjectViewModel getProject(long id);

    List<ProjectViewModel> getAllProjects();

    List<ProjectViewModel> getOwnProjects(Long userId);

    void assignDevelopers(ProjectChangeDevelopersForm form, Long projectId);

    void removeDevelopers(ProjectChangeDevelopersForm form, Long projectId);

    List<UserViewModel> getAssignedDevelopers(long projectId);

    List<UserViewModel> getAvailableDevelopers(Long projectId);

    List<ProjectViewModel> getProjectsThatIncludeDeveloper(Long id);

    List<ProjectViewModel> getProjectsThatIncludeQA(Long id);
}
