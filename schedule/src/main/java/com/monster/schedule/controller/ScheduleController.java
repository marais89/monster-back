package com.monster.schedule.controller;

import java.util.List;

import com.monster.schedule.dto.Period;
import com.monster.schedule.dto.ProjectDto;
import com.monster.schedule.dto.ScheduleDto;
import com.monster.schedule.facade.ScheduleFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "schedule")
@RestController
public class ScheduleController {


    public static final String BUSINESS_ID = "businessId";
    public static final String USERID = "userId";
    @Autowired
    private final ScheduleFacade scheduleFacade;

    public ScheduleController(ScheduleFacade scheduleFacade) {
        this.scheduleFacade = scheduleFacade;
    }

    @RequestMapping(path = "/projects/businessId/{businessId}", method = RequestMethod.GET)
    @ApiOperation(value = "get projects", authorizations = @Authorization("jwt"))
    public List<ProjectDto> findProjectByBusinessId(@PathVariable(BUSINESS_ID) int businessId) {
        return scheduleFacade.findByBusinessId(businessId);
    }

    @RequestMapping(path = "/schedule/userId/{userId}", method = RequestMethod.POST)
    @ApiOperation(value = "get daily planning by period", authorizations = @Authorization("jwt"))
    public List<ScheduleDto> retrievePlannigByPeriod(@PathVariable(USERID) Integer userId, @RequestBody Period period) {
        return scheduleFacade.retrieveDailyPlanningbyPeriod(userId, period);
    }

    @RequestMapping(path = "/schedule/save", method = RequestMethod.POST)
    @ApiOperation(value = "save plannings", authorizations = @Authorization("jwt"))
    public List<ScheduleDto> saveSchedule(@RequestBody List<ScheduleDto> schedules) {
        return scheduleFacade.save(schedules);
    }


}
