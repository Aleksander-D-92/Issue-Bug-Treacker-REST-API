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

    ProjectViewModel findOne(long id);

    List<ProjectViewModel> findALl();

    List<ProjectViewModel> findByProjectManager(Long userId);

    List<ProjectViewModel> getProjectsThatIncludeQA(Long id);

    void assignDevelopers(ProjectChangeDevelopersForm form, Long projectId);

    void removeDevelopers(ProjectChangeDevelopersForm form, Long projectId);

    List<UserViewModel> getAvailableDevelopers(Long projectId);

    List<ProjectViewModel> getProjectsThatIncludeDeveloper(Long id);
}
