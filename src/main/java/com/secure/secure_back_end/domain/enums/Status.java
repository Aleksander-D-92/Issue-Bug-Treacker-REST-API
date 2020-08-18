package com.secure.secure_back_end.domain.enums;

public enum Status
{
    NEW("New",0),
    IN_PROGRESS("In Progress", 1),
    RESOLVED("Resolved", 2);

    private final String status;
    private final int level;

    Status(String value, int level)
    {
        this.level = level;
        this.status = value;
    }
}
