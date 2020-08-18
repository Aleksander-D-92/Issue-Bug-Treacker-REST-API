package com.secure.secure_back_end.services.implementations;

import com.secure.secure_back_end.domain.Project;
import com.secure.secure_back_end.domain.User;
import com.secure.secure_back_end.dto.project.ProjectAssignDeveloperForm;
import com.secure.secure_back_end.dto.project.ProjectCreateForm;
import com.secure.secure_back_end.dto.project.ProjectEditForm;
import com.secure.secure_back_end.repositories.ProjectRepository;
import com.secure.secure_back_end.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl
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

    public void createProject(ProjectCreateForm projectCreateForm)
    {
        Project newProject = this.modelMapper.map(projectCreateForm, Project.class);
        User user = this.userRepository.findById(projectCreateForm.getUserId()).orElse(null);
        newProject.setProjectManager(user);
        newProject.setCreationDate(new Date());
        this.projectRepository.save(newProject);
    }

    public void editProject(ProjectEditForm projectEditForm)
    {
        Project project = this.projectRepository.findById(projectEditForm.getProjectId()).orElse(null);
        assert project != null;
        project.setTitle(projectEditForm.getTitle());
        project.setDescription(projectEditForm.getDescription());
        this.projectRepository.save(project);
    }

    public List<Project> getAllProjects()
    {
        List<Project> all = this.projectRepository.findAll();
        return all;
    }

    public List<Project> getOwnProjects(long userId)
    {
        User byId = this.userRepository.findById(userId).orElse(null);
        List<Project> byProjectManager = this.projectRepository.findByProjectManager(byId);
        String description = byProjectManager.get(0).getDescription();
        String title = byProjectManager.get(0).getTitle();
        return byProjectManager;
    }

    public void assignDevelopers(ProjectAssignDeveloperForm form)
    {
        List<User> developers = this.userRepository.findByIdIn(form.getDeveloperIds());
        Project project = this.projectRepository.findById(form.getProjectId()).orElse(null);
        assert project != null;
        developers.forEach(dev -> project.getAssignedPersonal().add(dev));
        this.projectRepository.save(project);
    }

    public void removeDevelopers()
    {

    }
}
