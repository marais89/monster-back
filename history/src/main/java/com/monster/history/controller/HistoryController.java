package com.monster.history.controller;

import com.monster.history.dto.ActionResult;
import com.monster.history.dto.ActionType;
import com.monster.history.dto.EventsDto;
import com.monster.history.dto.RequestContext;
import com.monster.history.facade.HistoryFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Valid
@Api(tags = "events history")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HistoryController {


    public static final String USERNAME = "username";
    public static final String ACTION_TYPE = "actionType";
    public static final String ACTION_RESULT = "actionResult";

    @Autowired
    private HistoryFacade historyFacade;

    @RequestMapping(path = "/history/username/{username}", method = RequestMethod.GET)
    @ApiOperation(value = "retrieve histories by username")
    List<EventsDto> retriveHistoryByUserName(@PathVariable(USERNAME) String username) {
        return historyFacade.retriveHistoryByUserName(username);
    }

    @RequestMapping(path = "/history/", method = RequestMethod.POST)
    @ApiOperation(value = "save history")
    EventsDto saveHistory(@RequestBody RequestContext request, @RequestBody ActionType type) {
        return historyFacade.saveHistory(request, type, ActionResult.INIT);
    }

    @RequestMapping(path = "/history/lastAuthentication/actionType/{actionType}/actionResult/{actionResult}", method = RequestMethod.GET)
    @ApiOperation(value = "retrieve last authentication")
    EventsDto retriveHistoryByUserName(@PathVariable(ACTION_TYPE) String actionType, @PathVariable(ACTION_RESULT) String actionResult) {
        return historyFacade.findLastAuthentication(actionType, actionResult);
    }

}