package com.secure.secure_back_end.services.interfaces;

import com.secure.secure_back_end.dto.project.binding.ProjectCreateForm;
import com.secure.secure_back_end.dto.project.binding.ProjectEditForm;
import com.secure.secure_back_end.dto.project.binding.ProjectQAForm;
import com.secure.secure_back_end.dto.project.view.ProjectViewModel;
import com.secure.secure_back_end.dto.user.view.UserAuthorityView;

import java.util.List;

public interface ProjectService
{
    void createProject(ProjectCreateForm projectCreateForm, Long userId);

    void editProject(ProjectEditForm projectEditForm, long projectId);

    ProjectViewModel findOne(long id);

    List<ProjectViewModel> findALl();

    List<ProjectViewModel> findByProjectManager(Long userId);

    List<ProjectViewModel> findIncludingQA(Long id);

    void addQaToProject(ProjectQAForm form, Long projectId);

    void removeQAFromProject(ProjectQAForm form, Long projectId);

    List<UserAuthorityView> findAvailableQaToAssign(Long projectId, Long managerId);

    List<UserAuthorityView> findAssignedQa(Long projectId);
}
