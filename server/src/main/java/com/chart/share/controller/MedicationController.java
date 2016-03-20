package com.chart.share.controller;

import com.chart.share.domain.GroupMember;
import com.chart.share.domain.Medication;
import com.chart.share.domain.Physician;
import com.chart.share.repository.GroupMemberRepository;
import com.chart.share.repository.MedicationRepository;
import com.chart.share.repository.PersonRepository;
import com.chart.share.repository.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by jenn on 3/16/16.
 */

@RestController
@RequestMapping("/service")
public class MedicationController {
    
    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @RequestMapping(value = "/medication/{id}", method = RequestMethod.GET)
    public Medication getMedication(@PathVariable long id) {
        Medication returnMedication;
        returnMedication = medicationRepository.findOne(id);
        return returnMedication;
    }

    @RequestMapping(value = "/medication", method = RequestMethod.GET)
    public List<Medication> getGroupMedications(@RequestParam long personId){
        List<Medication> groupMedication = null;
        GroupMember groupMember = groupMemberRepository.findByPersonId(personId);
        if(groupMember != null){
            long groupId = groupMember.getGroupId();
            groupMedication = medicationRepository.findByGroupId(groupId);
        }
        return groupMedication;
    }


    @RequestMapping(value = "/medication", method = RequestMethod.POST)
    public Medication createMedication(@RequestBody Medication medication) {
        long id = medication.getId();
        if(id == 0) {
            id = sequenceGenerator.invoke();
            medication.setId(id);
        }
        return medicationRepository.save(medication);
    }


    @RequestMapping(value = "medication/{id}", method = RequestMethod.DELETE)
    public Boolean deleteMedication(@PathVariable long id){
        medicationRepository.delete(id);
        return true;
    }
}
