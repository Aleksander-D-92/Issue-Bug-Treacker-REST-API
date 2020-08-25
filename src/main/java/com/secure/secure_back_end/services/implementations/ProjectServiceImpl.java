package com.secure.secure_back_end.services.implementations;

import com.secure.secure_back_end.domain.Authority;
import com.secure.secure_back_end.domain.Project;
import com.secure.secure_back_end.domain.User;
import com.secure.secure_back_end.dto.project.binding.ProjectChangeDevelopersForm;
import com.secure.secure_back_end.dto.project.binding.ProjectCreateForm;
import com.secure.secure_back_end.dto.project.binding.ProjectEditForm;
import com.secure.secure_back_end.dto.project.view.ProjectViewModel;
import com.secure.secure_back_end.dto.user.view.UserViewModel;
import com.secure.secure_back_end.repositories.ProjectRepository;
import com.secure.secure_back_end.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl
{
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private static final Long ROLE_DEVELOPER = 2L;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository, ModelMapper modelMapper)
    {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public void createProject(ProjectCreateForm projectCreateForm, Long userId)
    {
        Project newProject = this.modelMapper.map(projectCreateForm, Project.class);
        newProject.setId(null);
        User user = this.userRepository.getOne(userId);
        newProject.setProjectManager(user);
        newProject.setCreationDate(new Date());
        this.projectRepository.save(newProject);
    }

    public void editProject(ProjectEditForm projectEditForm, long projectId)
    {
        this.projectRepository.editProject(projectId, projectEditForm.getTitle(), projectEditForm.getDescription());
    }

    public ProjectViewModel getProject(long id)
    {
        Project project = this.projectRepository.findById(id).orElse(null);
        ProjectViewModel viewModel = this.modelMapper.map(project, ProjectViewModel.class);
        assert project != null;
        viewModel.setProjectManagerName(project.getProjectManager().getUsername());
        viewModel.setProjectManagerId(project.getProjectManager().getId());
        return viewModel;
    }

    public List<ProjectViewModel> getAllProjects()
    {
        List<Project> byProjectManager = this.projectRepository.getALlProjects();
        return byProjectManager.stream().map(project ->
        {
            ProjectViewModel viewModel = this.modelMapper.map(project, ProjectViewModel.class);
            User projectManager = project.getProjectManager();
            viewModel.setProjectManagerName(projectManager.getUsername());
            viewModel.setProjectManagerId(projectManager.getId());
            return viewModel;
        }).collect(Collectors.toList());
    }

    public List<ProjectViewModel> getOwnProjects(Long userId)
    {
        List<Project> byProjectManager = this.projectRepository.getALlProjectsByOwnerId(userId);
        return byProjectManager.stream().map(project ->
        {
            ProjectViewModel viewModel = this.modelMapper.map(project, ProjectViewModel.class);
            viewModel.setProjectManagerName(project.getProjectManager().getUsername());
            viewModel.setProjectManagerId(project.getProjectManager().getId());
            return viewModel;
        }).collect(Collectors.toList());
    }

    public void assignDevelopers(ProjectChangeDevelopersForm form, Long projectId)
    {
        List<Long> developerIds = form.getDeveloperIds();
        developerIds.forEach(devId -> this.projectRepository.addDevelopersToProject(projectId, devId));
    }

    public void removeDevelopers(ProjectChangeDevelopersForm form, Long projectId)
    {
        List<Long> developerIds = form.getDeveloperIds();
        this.projectRepository.removeDevelopersFromProject(projectId, developerIds);
    }

    public List<UserViewModel> getAssignedDevelopers(long id)
    {
        Project assignedDevelopers = this.projectRepository.getAssignedDevelopers(id);
        return assignedDevelopers.getAssignedDevelopers().stream()
                .map(this::mapToUserViewModel)
                .collect(Collectors.toList());
    }

    public List<UserViewModel> getAvailableDevelopers(Long projectId)
    {
        List<Long> devIds = this.projectRepository.getAssignedDevelopersIds(projectId);
        return this.userRepository.getUserDetailsByRole(ROLE_DEVELOPER).stream()
                .filter(dev -> !devIds.contains(dev.getId()))
                .map(this::mapToUserViewModel)
                .collect(Collectors.toList());
    }

    private UserViewModel mapToUserViewModel(User user)
    {
        UserViewModel userViewModel = this.modelMapper.map(user, UserViewModel.class);
        Authority highestAuthority = user.getAuthorities().stream().reduce((e1, e2) -> e1.getAuthorityLevel() > e2.getAuthorityLevel() ? e1 : e2).get();
        userViewModel.setAuthority(highestAuthority);
        return userViewModel;
    }
}
