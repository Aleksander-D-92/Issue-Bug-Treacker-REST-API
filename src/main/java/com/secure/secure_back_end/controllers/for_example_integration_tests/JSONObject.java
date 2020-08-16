package com.secure.secure_back_end.controllers.for_example_integration_tests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class JSONObject
{
    @NotNull
    @Size(min = 4, max = 15, message = "must be between 4 and 15 symbols")
    private String title;
    @NotNull
    @Size(min = 4, max = 15, message = "must be between 4 and 15 symbols")
    private String value;

    public JSONObject()
    {
    }

    public JSONObject(String title, String value)
    {
        this.title = title;
        this.value = value;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "JSONObject{" +
                "title='" + title + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
