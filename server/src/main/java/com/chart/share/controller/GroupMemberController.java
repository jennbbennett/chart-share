package com.chart.share.controller;

import com.chart.share.domain.GroupMember;
import com.chart.share.repository.GroupMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by jenn on 3/15/16.
 */

@RestController
@RequestMapping("/service")
public class GroupMemberController {

    @Autowired
    private GroupMemberRepository groupMemberRepository;


    @RequestMapping(value = "/groupmember/{groupid}/{personid}", method = RequestMethod.POST )
    public GroupMember addPersonToGroup(@PathVariable long groupid, @PathVariable long personid){
        GroupMember groupMember = new GroupMember(groupid, personid, new Date());
        groupMember = groupMemberRepository.save(groupMember);
        return groupMember;
    }

    @RequestMapping(value = "/groupmember/{groupid}/{personid}", method = RequestMethod.DELETE )
    public Boolean deletePersonFromGroup(@PathVariable long groupid, @PathVariable long personid){
        groupMemberRepository.delete(groupMemberRepository.findByPersonId(personid));
        return true;
    }

}
