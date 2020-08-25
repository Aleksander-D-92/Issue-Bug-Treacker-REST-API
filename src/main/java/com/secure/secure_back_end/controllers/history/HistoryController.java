package com.secure.secure_back_end.controllers.history;

import com.secure.secure_back_end.dto.history.view.HistoryViewModel;
import com.secure.secure_back_end.services.interfaces.HistoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
public class HistoryController
{
    private final HistoryService historyService;

    public HistoryController(HistoryService historyService)
    {
        this.historyService = historyService;
    }

    @GetMapping("/tickets/history/{ticketId}")
    @ApiOperation(value = "returns the history of the given ticket")
    public List<HistoryViewModel> getHistoryForTicket(@PathVariable("ticketId") @Min(1) Long ticketId)
    {
        return this.historyService.getHistoryForTicket(ticketId);
    }
}
