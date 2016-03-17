package com.chart.share.controller;

import com.chart.share.domain.Group;
import com.chart.share.domain.GroupMember;
import com.chart.share.domain.Person;
import com.chart.share.repository.GroupMemberRepository;
import com.chart.share.repository.GroupRepository;
import com.chart.share.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jenn on 3/14/16.
 */

@RestController
@RequestMapping("/service")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @RequestMapping(value = "/findgroup")
    public FindGroupResult findGroup(@RequestParam("personId") long personId) {
        long groupId = groupMemberRepository.findByPersonId(personId).getGroupId();
        Group group = groupRepository.findOne(groupId);
        List<GroupMember> groupMembers = groupMemberRepository.findByGroupId(groupId);
        List<Person> members = new LinkedList<>();
        for (GroupMember member : groupMembers) {
            members.add(personRepository.findOne(member.getPersonId()));
        }
        return new FindGroupResult(group,members);
    }

    class FindGroupResult {
        Group group;
        List<Person> members;

        public FindGroupResult(Group group, List<Person> members) {
            this.group = group;
            this.members = members;
        }

        public Group getGroup() {
            return group;
        }

        public List<Person> getMembers() {
            return members;
        }
    }



    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    public Group getGroup(@PathVariable long id) {
        Group returnGroup;
        returnGroup = groupRepository.findOne(id);
        return returnGroup;
    }

    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public Group createGroup(@RequestBody Group group) {
        long id = group.getId();
        if(id ==0) {
            id = sequenceGenerator.invoke();
            group.setId(id);
        }
        return groupRepository.save(group);
    }

    @RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
    public boolean deleteGroup(@PathVariable Long id) {
        return true;
    }
}
