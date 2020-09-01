package com.secure.secure_back_end.domain;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", indexes = {@Index(name = "username_index", columnList = "username")})
@NamedEntityGraphs({
        @NamedEntityGraph(name = "pesho", attributeNodes = {@NamedAttributeNode("authorities")})
})
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column
    private boolean accountNonLocked;
    @Column
    private Date registrationDate;
    @OneToMany(mappedBy = "projectManager", fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = Project.class)
    List<Project> ownedProjects;
    @OneToMany(mappedBy = "submitter", fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = Ticket.class)
    List<Ticket> tickets;
    @OneToMany(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "manager_id")
    List<User> staff;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_authorities",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "authorityId")
    )
    private Set<Authority> authorities;

    public List<User> getStaff()
    {
        return staff;
    }

    public void setStaff(List<User> staff)
    {
        this.staff = staff;
    }

    public List<Ticket> getTickets()
    {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets)
    {
        this.tickets = tickets;
    }

    public List<Project> getOwnedProjects()
    {
        return ownedProjects;
    }

    public void setOwnedProjects(List<Project> ownedProjects)
    {
        this.ownedProjects = ownedProjects;
    }

    public User()
    {
        this.authorities = new HashSet<>();
    }


    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long id)
    {
        this.userId = id;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setAuthorities(Set<Authority> authorities)
    {
        this.authorities = authorities;
    }

    public Date getRegistrationDate()
    {
        return registrationDate;
    }

    public void setRegistrationDate(Date creationDate)
    {
        this.registrationDate = creationDate;
    }

    @Override
    public Set<Authority> getAuthorities()
    {
        return authorities;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return this.accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked)
    {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
