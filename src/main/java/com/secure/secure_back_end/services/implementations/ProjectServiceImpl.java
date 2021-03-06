package com.secure.secure_back_end.services.implementations;

import com.secure.secure_back_end.domain.Project;
import com.secure.secure_back_end.domain.User;
import com.secure.secure_back_end.dto.project.binding.ProjectCreateForm;
import com.secure.secure_back_end.dto.project.binding.ProjectEditForm;
import com.secure.secure_back_end.dto.project.binding.ProjectQAForm;
import com.secure.secure_back_end.dto.project.view.ProjectDetailsView;
import com.secure.secure_back_end.dto.user.view.UserDetailsView;
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
    public ProjectDetailsView findOne(long id)
    {
        Project project = this.projectRepository.findByProjectId(id);
        return this.modelMapper.map(project, ProjectDetailsView.class);
    }

    @Override
    public List<ProjectDetailsView> findALl()
    {
        return this.projectRepository.findAllBy().stream()
                .map(project -> this.modelMapper.map(project, ProjectDetailsView.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDetailsView> findByProjectManager(Long userId)
    {
        User user = this.userRepository.getOne(userId);
        return this.projectRepository.findAllByProjectManager(user).stream()
                .map(project -> this.modelMapper.map(project, ProjectDetailsView.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<ProjectDetailsView> findIncludingQA(Long id)
    {
        List<Long> ids = this.projectRepository.findProjectIdsThatIncludeQA(id);
        return this.projectRepository.findAllByProjectIdIn(ids).stream()
                .map(project -> this.modelMapper.map(project, ProjectDetailsView.class))
                .collect(Collectors.toList());
    }

    public List<UserDetailsView> findAvailableQaToAssign(Long projectId, Long managerId)
    {
        List<Long> qaIdsForManger = this.projectRepository.getQaIdsForManger(managerId);
        List<Long> qaIdsForProject = this.projectRepository.getQaIdsForProject(projectId);
        List<Long> qaIdsAvailable = qaIdsForManger.stream().filter(id -> !qaIdsForProject.contains(id)).collect(Collectors.toList());

        return this.userRepository.findAllByUserIdIn(qaIdsAvailable).stream()
                .map(user ->
                {
                    UserDetailsView map = this.modelMapper.map(user, UserDetailsView.class);
                    map.setAuthority(user.getAuthorities().iterator().next());
                    return map;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDetailsView> findAssignedQa(Long projectId)
    {
        List<Long> ids = this.projectRepository.getQaIdsForProject(projectId);
        return this.userRepository.findAllByUserIdIn(ids).stream()
                .map(user ->
                {
                    UserDetailsView map = this.modelMapper.map(user, UserDetailsView.class);
                    map.setAuthority(user.getAuthorities().iterator().next());
                    return map;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDetailsView> findDevelopers(Long managerId)
    {
        User one = this.userRepository.getOne(managerId);
        return this.userRepository.findAllByManager(one).stream()
                .filter(user -> user.getAuthorities().iterator().next().getAuthority().equals("ROLE_DEVELOPER"))
                .map(user ->
                {
                    UserDetailsView map = this.modelMapper.map(user, UserDetailsView.class);
                    map.setAuthority(user.getAuthorities().iterator().next());
                    return map;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addQaToProject(ProjectQAForm form, Long projectId)
    {
        form.getQaIds().forEach(devId -> this.projectRepository.addQaToProject(projectId, devId));
    }

    @Override
    public void removeQAFromProject(ProjectQAForm form, Long projectId)
    {
        this.projectRepository.removeQaFromProject(projectId, form.getQaIds());
    }

}
