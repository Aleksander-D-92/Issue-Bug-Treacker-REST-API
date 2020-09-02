package com.secure.secure_back_end.services.implementations;

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

import java.util.ArrayList;
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
    public ProjectViewModel findOne(long id)
    {
        Project project = this.projectRepository.findByProjectId(id);
        return this.modelMapper.map(project, ProjectViewModel.class);
    }

    @Override
    public List<ProjectViewModel> findALl()
    {
        return this.projectRepository.findAllBy().stream()
                .map(project -> this.modelMapper.map(project, ProjectViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectViewModel> findByProjectManager(Long userId)
    {
//        User user = this.userRepository.findById(userId).orElse(null);
        User user = this.userRepository.getOne(userId);
        return this.projectRepository.findAllByProjectManager(user).stream()
                .map(project -> this.modelMapper.map(project, ProjectViewModel.class))
                .collect(Collectors.toList());
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

    //todo remove or?
    @Override
    public List<UserViewModel> getAvailableDevelopers(Long projectId)
    {
        return new ArrayList<>();
    }

    //todo remove or?
    @Override
    public List<ProjectViewModel> getProjectsThatIncludeDeveloper(Long id)
    {
//        List<Long> ids = this.projectRepository.getProjectIdsThatIncludeDeveloper(id);
//        return this.projectRepository.getAllByIdsIn(ids).stream().map(project ->
//        {
//            ProjectViewModel viewModel = this.modelMapper.map(project, ProjectViewModel.class);
//            viewModel.setProjectManagerName(project.getProjectManager().getUsername());
//            viewModel.setProjectManagerId(project.getProjectManager().getUserId());
//            return viewModel;
//        }).collect(Collectors.toList());
        return new ArrayList<>();
    }

    @Override
    public List<ProjectViewModel> getProjectsThatIncludeQA(Long id)
    {
        List<Long> ids = this.projectRepository.getProjectIdsThatIncludeQA(id);
        return this.projectRepository.findAllByProjectIdIn(ids).stream()
                .map(project -> this.modelMapper.map(project, ProjectViewModel.class))
                .collect(Collectors.toList());
    }

}
