package com.chart.share.controller;

import com.chart.share.domain.Medication;
import com.chart.share.repository.MedicationRepository;
import com.chart.share.repository.PersonRepository;
import com.chart.share.repository.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by jenn on 3/16/16.
 */

@RestController
@RequestMapping("/service")
public class MedicationController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @RequestMapping(value = "/medication/{id}", method = RequestMethod.GET)
    public Medication getMedication(@PathVariable long id) {
        Medication returnMedication;
        returnMedication = medicationRepository.findOne(id);
        return returnMedication;
    }

    @RequestMapping(value = "/medication", method = RequestMethod.POST)
    public Medication createMedication(@RequestBody Medication medication) {
        long id = sequenceGenerator.invoke();
        medication.setId(id);
        return medicationRepository.save(medication);
    }

    @RequestMapping(value = "/medication/{id}", method = RequestMethod.POST)
    public Medication updateMedication(@PathVariable long id, @RequestBody Medication medication){
        Medication updateMedication;
        updateMedication = medicationRepository.findOne(id);
        updateMedication = medicationRepository.save(medication);
        return updateMedication;
    }

    @RequestMapping(value = "medication/{id}", method = RequestMethod.DELETE)
    public Boolean deleteMedication(@PathVariable long id){
        medicationRepository.delete(id);
        return true;
    }
}
