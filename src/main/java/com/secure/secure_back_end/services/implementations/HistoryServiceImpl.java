package com.secure.secure_back_end.services.implementations;

import com.secure.secure_back_end.dto.history.view.HistoryViewModel;
import com.secure.secure_back_end.repositories.HistoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl
{
    private final HistoryRepository historyRepository;
    private final ModelMapper modelMapper;

    public HistoryServiceImpl(HistoryRepository historyRepository, ModelMapper modelMapper)
    {
        this.historyRepository = historyRepository;
        this.modelMapper = modelMapper;
    }

    public List<HistoryViewModel> getHistoryForTicket(Long ticketId)
    {
        return this.historyRepository.getHistoryForTicket(ticketId).stream()
                .map(history ->
                {
                    HistoryViewModel map = this.modelMapper.map(history, HistoryViewModel.class);
                    if (history.getAssignedDeveloper() != null)
                    {
                        map.setAssignedDeveloperId(history.getAssignedDeveloper().getId());
                        map.setAssignedDeveloperName(history.getAssignedDeveloper().getUsername());
                    }
                    return map;
                }).collect(Collectors.toList());
    }
}
