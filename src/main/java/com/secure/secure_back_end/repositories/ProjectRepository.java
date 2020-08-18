package com.secure.secure_back_end.repositories;

import com.secure.secure_back_end.domain.Project;
import com.secure.secure_back_end.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long>
{
    List<Project> findByProjectManager(User user);
}
