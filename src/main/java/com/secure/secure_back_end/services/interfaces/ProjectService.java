package com.secure.secure_back_end.services.interfaces;

import com.secure.secure_back_end.dto.project.binding.ProjectCreateForm;
import com.secure.secure_back_end.dto.project.binding.ProjectEditForm;
import com.secure.secure_back_end.dto.project.binding.ProjectQAForm;
import com.secure.secure_back_end.dto.project.view.ProjectDetailsView;
import com.secure.secure_back_end.dto.user.view.UserDetailsView;

import java.util.List;

public interface ProjectService
{
    void createProject(ProjectCreateForm projectCreateForm, Long userId);

    void editProject(ProjectEditForm projectEditForm, long projectId);

    ProjectDetailsView findOne(long id);

    List<ProjectDetailsView> findALl();

    List<ProjectDetailsView> findByProjectManager(Long userId);

    List<ProjectDetailsView> findIncludingQA(Long id);

    List<UserDetailsView> findAvailableQaToAssign(Long projectId, Long managerId);

    void addQaToProject(ProjectQAForm form, Long projectId);

    void removeQAFromProject(ProjectQAForm form, Long projectId);

    List<UserDetailsView> findAssignedQa(Long projectId);
}
