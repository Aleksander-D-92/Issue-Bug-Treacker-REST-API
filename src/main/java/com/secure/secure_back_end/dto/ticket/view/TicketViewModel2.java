package com.secure.secure_back_end.dto.ticket.view;

import com.secure.secure_back_end.domain.enums.Category;
import com.secure.secure_back_end.domain.enums.Priority;
import com.secure.secure_back_end.domain.enums.Status;
import com.secure.secure_back_end.dto.user.view.UserView;

import java.util.Date;

public class TicketViewModel2
{
    private UserView submitter;
    private UserView assignedDeveloper;
    private Long ticketId;
    private String title;
    private String projectTitle;
    private Priority priority;
    private Status status;
    private Category category;
    private Date creationDate;

}
