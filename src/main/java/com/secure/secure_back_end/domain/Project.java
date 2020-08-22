package com.secure.secure_back_end.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "projects")
public class Project
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", name = "project_manager_id")
    private User projectManager;
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "varchar(255)")
    private String description;
    @Column
    private Date creationDate;
    @OneToMany(mappedBy = "project", targetEntity = Ticket.class, fetch = FetchType.LAZY)
    private List<Ticket> tickets;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "projects_developers",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="user_id", referencedColumnName="id"))
    private Set<User> assignedDevelopers;

    public Project()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public User getProjectManager()
    {
        return projectManager;
    }

    public void setProjectManager(User projectManager)
    {
        this.projectManager = projectManager;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public List<Ticket> getTickets()
    {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets)
    {
        this.tickets = tickets;
    }

    public Set<User> getAssignedDevelopers()
    {
        return assignedDevelopers;
    }

    public void setAssignedDevelopers(Set<User> assignedPersonal)
    {
        this.assignedDevelopers = assignedPersonal;
    }
}
