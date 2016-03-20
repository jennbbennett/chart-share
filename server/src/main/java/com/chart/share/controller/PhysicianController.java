package com.chart.share.controller;

import com.chart.share.domain.GroupMember;
import com.chart.share.domain.Physician;
import com.chart.share.repository.GroupMemberRepository;
import com.chart.share.repository.PersonRepository;
import com.chart.share.repository.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jenn on 3/16/16.
 */

@RestController
@RequestMapping("/service")
public class PhysicianController {

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @RequestMapping(value = "/physician/{id}", method = RequestMethod.GET)
    public Physician getPhysician(@PathVariable long id){
        Physician returnPhysician;
        returnPhysician = physicianRepository.findOne(id);
        return returnPhysician;
    }


//    trying to find all physicians linked to group
    @RequestMapping(value = "/physician", method = RequestMethod.GET)
    public  List<Physician> getGroupPhysicians(@RequestParam long personId){
        List<Physician> groupPhysician = null;
        GroupMember groupMember = groupMemberRepository.findByPersonId(personId);
        if(groupMember != null){
            long groupId = groupMember.getGroupId();
            groupPhysician = physicianRepository.findByGroupId(groupId);
        }
        return groupPhysician;
    }

    @RequestMapping(value = "/physician", method = RequestMethod.POST)
    public Physician createPhysician(@RequestBody Physician physician){
        long id = physician.getId();
        if(id ==0) {
            id = sequenceGenerator.invoke();
            physician.setId(id);
        }
        return physicianRepository.save(physician);
    }

    @RequestMapping(value = "physician/{id}", method = RequestMethod.DELETE)
    public Boolean deletePhysician(@PathVariable long id){
        physicianRepository.delete(id);
        return true;
    }
}
