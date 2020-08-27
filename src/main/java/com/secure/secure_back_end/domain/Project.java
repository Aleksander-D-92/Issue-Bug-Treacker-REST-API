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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "project_manager_id")
    private User projectManager;
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "varchar(255)")
    private String description;
    @Column
    private Date creationDate;
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = Ticket.class)
    private List<Ticket> tickets;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "projects_developers",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id", referencedColumnName = "id"))
    private Set<User> assignedDevelopers;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "projects_qa",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "qa_id", referencedColumnName = "id"))
    private Set<User> assignedQa;

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

    public Set<User> getAssignedQa()
    {
        return assignedQa;
    }

    public void setAssignedQa(Set<User> assignedSubmitters)
    {
        this.assignedQa = assignedSubmitters;
    }
}
