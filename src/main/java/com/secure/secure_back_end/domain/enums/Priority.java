package com.secure.secure_back_end.domain.enums;

public enum Priority
{
    LOW("Low", 0),
    MEDIUM("Medium", 1),
    HIGH("High", 2),
    URGENT("Urgent", 3);
    private final String priority;
    private final int level;

    Priority(String priority, int level)
    {
        this.priority = priority;
        this.level = level;
    }
}
