package com.secure.secure_back_end.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "projects")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "fetchProjectManager", attributeNodes = {
                @NamedAttributeNode(value = "projectManager")
        })
})
public class Project
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "userId", name = "project_manager_id")
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
    @JoinTable(name = "projects_qa",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "projectId"),
            inverseJoinColumns = @JoinColumn(name = "qa_id", referencedColumnName = "userId"))
    private Set<User> assignedQa;

    public Project()
    {
    }

    public Long getProjectId()
    {
        return projectId;
    }

    public void setProjectId(Long id)
    {
        this.projectId = id;
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

    public Set<User> getAssignedQa()
    {
        return assignedQa;
    }

    public void setAssignedQa(Set<User> assignedSubmitters)
    {
        this.assignedQa = assignedSubmitters;
    }
}
