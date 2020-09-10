package com.secure.secure_back_end.dto.rest_success;

public class Message
{
    private String message;

    public Message()
    {
    }

    public Message(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
