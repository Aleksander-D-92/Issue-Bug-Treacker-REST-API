package com.secure.secure_back_end.domain;

import com.secure.secure_back_end.domain.enums.Category;
import com.secure.secure_back_end.domain.enums.Priority;
import com.secure.secure_back_end.domain.enums.Status;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "tickets")
public class Ticket
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "varchar(255)")
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column
    private Date creationDate;
    @OneToMany(mappedBy = "ticket", targetEntity = Comment.class, fetch = FetchType.LAZY)
    private List<Comment> comments;
    @OneToMany(mappedBy = "ticket", targetEntity = History.class, fetch = FetchType.LAZY)
    private List<History> history;
    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(referencedColumnName = "id", name = "project_id")
    private Project project;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "assigned_developer_id", referencedColumnName = "id")
    private User assignedDeveloper;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "submitter_id", referencedColumnName = "id")
    private User submitter;


    public Ticket()
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

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public Priority getPriority()
    {
        return priority;
    }

    public void setPriority(Priority priority)
    {
        this.priority = priority;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public List<Comment> getComments()
    {
        return comments;
    }

    public void setComments(List<Comment> comments)
    {
        this.comments = comments;
    }

    public List<History> getHistory()
    {
        return history;
    }

    public void setHistory(List<History> history)
    {
        this.history = history;
    }

    public Project getProject()
    {
        return project;
    }

    public void setProject(Project project)
    {
        this.project = project;
    }

    public User getAssignedDeveloper()
    {
        return assignedDeveloper;
    }

    public void setAssignedDeveloper(User assignedDeveloper)
    {
        this.assignedDeveloper = assignedDeveloper;
    }

    public User getSubmitter()
    {
        return submitter;
    }

    public void setSubmitter(User submitter)
    {
        this.submitter = submitter;
    }
}
