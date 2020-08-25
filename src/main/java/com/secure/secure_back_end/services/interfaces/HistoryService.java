package com.secure.secure_back_end.services.interfaces;

import com.secure.secure_back_end.dto.history.view.HistoryViewModel;

import java.util.List;

public interface HistoryService
{
    List<HistoryViewModel> getHistoryForTicket(Long ticketId);
}
