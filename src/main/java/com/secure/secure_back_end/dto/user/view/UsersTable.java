package com.secure.secure_back_end.dto.user.view;

import java.util.List;

public class UsersTable
{
    private List<UserViewModel> users;
    private int totalCount;

    public UsersTable()
    {
    }

    public UsersTable(List<UserViewModel> users, int totalCount)
    {
        this.users = users;
        this.totalCount = totalCount;
    }

    public List<UserViewModel> getUsers()
    {
        return users;
    }

    public void setUsers(List<UserViewModel> users)
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
