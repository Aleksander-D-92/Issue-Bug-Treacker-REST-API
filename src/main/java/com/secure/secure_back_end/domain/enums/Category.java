package com.secure.secure_back_end.domain.enums;

public enum Category
{
    BUGS_AND_ERRORS("Bugs and Errors"),
    FEATURE_REQUEST("Feature Request"),
    OTHER("Other");

    private final String category;

    Category(String category)
    {
        this.category = category;
    }
}
