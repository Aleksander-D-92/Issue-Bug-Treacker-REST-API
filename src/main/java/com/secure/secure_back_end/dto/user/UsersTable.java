package com.secure.secure_back_end.dto.user;

import java.util.List;

public class UsersTable
{
    private List<UserAuthorityDetails> users;
    private int totalCount;

    public UsersTable()
    {
    }

    public UsersTable(List<UserAuthorityDetails> users, int totalCount)
    {
        this.users = users;
        this.totalCount = totalCount;
    }

    public List<UserAuthorityDetails> getUsers()
    {
        return users;
    }

    public void setUsers(List<UserAuthorityDetails> users)
    {
        this.users = users;
    }

    public int getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }

    @Override
    public String
    toString()
    {
        return "UsersPageAndTotalCount{" +
                "users=" + users +
                ", totalCount=" + totalCount +
                '}';
    }
}
