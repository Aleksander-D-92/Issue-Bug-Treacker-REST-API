package com.secure.secure_back_end.services.interfaces;

import com.secure.secure_back_end.dto.history.view.HistoryDetailsView;

import java.util.List;

public interface HistoryService
{
    List<HistoryDetailsView> findAllByTicket(Long ticketId);
}
