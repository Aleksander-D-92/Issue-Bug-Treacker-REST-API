package com.secure.secure_back_end.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "projects")
public class Project
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "project_manager_id")
    private User projectManager;
    @Column(nullable = false, columnDefinition = "varchar(45)")
    private String title;
    @Column(columnDefinition = "varchar(255)")
    private String description;
    @Column
    private Date creationDate;
    @OneToMany(mappedBy = "project", targetEntity = Ticket.class)
    private List<Ticket> tickets;
    @ManyToMany
    @JoinTable(name = "projects_developers",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="user_id", referencedColumnName="id"))
    private List<User> assignedPersonal;

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

    public List<User> getAssignedPersonal()
    {
        return assignedPersonal;
    }

    public void setAssignedPersonal(List<User> assignedPersonal)
    {
        this.assignedPersonal = assignedPersonal;
    }
}
