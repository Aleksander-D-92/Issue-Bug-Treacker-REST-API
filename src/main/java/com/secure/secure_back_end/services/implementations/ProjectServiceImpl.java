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
import com.secure.secure_back_end.services.interfaces.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService
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

    @Override
    public void createProject(ProjectCreateForm projectCreateForm, Long userId)
    {
        Project newProject = this.modelMapper.map(projectCreateForm, Project.class);
        newProject.setProjectId(null);
        User user = this.userRepository.getOne(userId);
        newProject.setProjectManager(user);
        newProject.setCreationDate(new Date());
        this.projectRepository.save(newProject);
    }

    @Override
    public void editProject(ProjectEditForm projectEditForm, long projectId)
    {
        this.projectRepository.editProject(projectId, projectEditForm.getTitle(), projectEditForm.getDescription());
    }

    @Override
    public ProjectViewModel getProject(long id)
    {
        Project project = this.projectRepository.getSingle(id);
        assert project != null;
        ProjectViewModel viewModel = this.modelMapper.map(project, ProjectViewModel.class);
        viewModel.setProjectManagerName(project.getProjectManager().getUsername());
        viewModel.setProjectManagerId(project.getProjectManager().getUserId());
        return viewModel;
    }

    @Override
    public List<ProjectViewModel> getAllProjects()
    {
        List<Project> byProjectManager = this.projectRepository.getAll();
        return byProjectManager.stream().map(project ->
        {
            ProjectViewModel map = this.modelMapper.map(project, ProjectViewModel.class);
            User projectManager = project.getProjectManager();
            map.setProjectManagerName(projectManager.getUsername());
            map.setProjectManagerId(projectManager.getUserId());
            return map;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProjectViewModel> getOwnProjects(Long userId)
    {
        List<Project> byProjectManager = this.projectRepository.getALlByOwnerId(userId);
        return byProjectManager.stream().map(project ->
        {
            ProjectViewModel viewModel = this.modelMapper.map(project, ProjectViewModel.class);
            viewModel.setProjectManagerName(project.getProjectManager().getUsername());
            viewModel.setProjectManagerId(project.getProjectManager().getUserId());
            return viewModel;
        }).collect(Collectors.toList());
    }

    @Override
    public void assignDevelopers(ProjectChangeDevelopersForm form, Long projectId)
    {
        List<Long> developerIds = form.getDeveloperIds();
        developerIds.forEach(devId -> this.projectRepository.addDevelopersToProject(projectId, devId));
    }

    @Override
    public void removeDevelopers(ProjectChangeDevelopersForm form, Long projectId)
    {
        List<Long> developerIds = form.getDeveloperIds();
        this.projectRepository.removeDevelopersFromProject(projectId, developerIds);
    }

    @Override
    public List<UserViewModel> getAssignedDevelopers(long projectId)
    {
        Project assignedDevelopers = this.projectRepository.getAssignedDevelopers(projectId);
        return assignedDevelopers.getAssignedDevelopers().stream()
                .map(this::mapToUserViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserViewModel> getAvailableDevelopers(Long projectId)
    {
        List<Long> devIds = this.projectRepository.getAssignedDevelopersIds(projectId);
        return this.userRepository.getAllByAuthority(ROLE_DEVELOPER).stream()
                .filter(dev -> !devIds.contains(dev.getUserId()))
                .map(this::mapToUserViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectViewModel> getProjectsThatIncludeDeveloper(Long id)
    {
        List<Long> ids = this.projectRepository.getProjectIdsThatIncludeDeveloper(id);
        return this.projectRepository.getAllByIdsIn(ids).stream().map(project ->
        {
            ProjectViewModel viewModel = this.modelMapper.map(project, ProjectViewModel.class);
            viewModel.setProjectManagerName(project.getProjectManager().getUsername());
            viewModel.setProjectManagerId(project.getProjectManager().getUserId());
            return viewModel;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProjectViewModel> getProjectsThatIncludeQA(Long id)
    {
        List<Long> ids = this.projectRepository.getProjectIdsThatIncludeQA(id);
        return this.projectRepository.getAllByIdsIn(ids).stream().map(project ->
        {
            ProjectViewModel viewModel = this.modelMapper.map(project, ProjectViewModel.class);
            viewModel.setProjectManagerName(project.getProjectManager().getUsername());
            viewModel.setProjectManagerId(project.getProjectManager().getUserId());
            return viewModel;
        }).collect(Collectors.toList());
    }

    private UserViewModel mapToUserViewModel(User user)
    {
        UserViewModel userViewModel = this.modelMapper.map(user, UserViewModel.class);
        Authority highestAuthority = user.getAuthorities().stream().reduce((e1, e2) -> e1.getAuthorityLevel() > e2.getAuthorityLevel() ? e1 : e2).get();
//        userViewModel.setAuthority(highestAuthority);
        return userViewModel;
    }
}
