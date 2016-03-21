package com.chart.share.controller;

import com.chart.share.domain.Activity;
import com.chart.share.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jenn on 3/21/16.
 */

@RestController
@RequestMapping("/service")

public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @RequestMapping(value= "/activity/group/{groupId}", method = RequestMethod.GET)
    public List<Activity> getActivityForGroup(@PathVariable long groupId){
        return activityRepository.findByGroupIdOrderByActivityDateDesc(groupId);
    }



}
