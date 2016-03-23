package com.chart.share.controller;

import com.chart.share.domain.*;
import com.chart.share.repository.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by jenn on 3/16/16.
 */

@RestController
@RequestMapping("/service")
public class PhysicianController {

    Logger logger = LoggerFactory.getLogger(PhysicianController.class);

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ActivityRepository activityRepository;


    @RequestMapping(value = "/physician/{id}", method = RequestMethod.GET)
    public Physician getPhysician(@PathVariable long id) {
        Physician returnPhysician;
        returnPhysician = physicianRepository.findOne(id);
        return returnPhysician;
    }

    @RequestMapping(value = "/physician/patients/{physicianId}", method = RequestMethod.GET)
    public List<PatientResult> getPatientsByPhysicianId(@PathVariable long physicianId) {
        List<Patient> patients;
        List<PatientResult> results = new LinkedList<>();

        patients = patientRepository.findByPhysicianId(physicianId);

        for (Patient patient : patients) {
            Person person = personRepository.findOne(patient.getPersonId());
            results.add(new PatientResult(person, patient.getDateAdded()));
        }
        return results;
    }

    class PatientResult {
        Person person;
        @JsonFormat(pattern = "MM-dd-yyyy")
        Date dateAdded;

        public PatientResult(Person person, Date dateAdded) {
            this.person = person;
            this.dateAdded = dateAdded;
        }

        public Person getPerson() {
            return person;
        }

        public Date getDateAdded() {
            return dateAdded;
        }
    }


    //    trying to find all physicians linked to group
    @RequestMapping(value = "/physician", method = RequestMethod.GET)
    public List<Physician> getGroupPhysicians(@RequestParam long personId) {
        List<Physician> groupPhysician = null;
        GroupMember groupMember = groupMemberRepository.findByPersonId(personId);
        if (groupMember != null) {
            long groupId = groupMember.getGroupId();
            groupPhysician = physicianRepository.findByGroupId(groupId);
        }
        return groupPhysician;
    }

    @RequestMapping(value = "/physician", method = RequestMethod.PUT)
    public Physician updatePhysician(@RequestBody PhysicianSaveData physicianSaveData) {
        long id = physicianSaveData.getId();
        String activityDescription = "Updated Physician";
        if (id == 0) {
            id = sequenceGenerator.invoke();
            physicianSaveData.setId(id);
            activityDescription = "Added Physician";
        }
        Physician physician = physicianRepository.save(physicianSaveData);
        activityRepository.save(new Activity(DomainType.PHYSICIAN,
                physician.getId(),
                new Date(),
                activityDescription,
                physician.getGroupId()));

        long[] patients = physicianSaveData.getPatients();
//        patientRepository.deleteByPhysicianId(physician.getId());
        List<Patient> existingPatients = patientRepository.findByPhysicianId(physician.getId());
        Map<Long, Patient> ePatientMap = new HashMap<>();

        for (Patient patient : existingPatients) {
            ePatientMap.put(patient.getPersonId(), patient);
        }

        Set<Long> toAdd = new HashSet<Long>();
        Set<Long> toDelete = new HashSet<Long>();
        for (Patient patient : ePatientMap.values()) {
            toDelete.add(patient.getPersonId());
        }

        for (long pid : patients) {
            if (ePatientMap.get(pid) != null) {
                toDelete.remove(pid);
            } else {
                toAdd.add(pid);
            }
        }

        for (long pid : toAdd) {
            patientRepository.save(new Patient(physician.getId(), pid, new Date()));
        }

        for (long pid : toDelete) {
            patientRepository.deleteByPersonId(pid);
        }

        return physician;
    }

    @RequestMapping(value = "/physician", method = RequestMethod.POST)
    public Physician createPhysician(@RequestBody Physician physician) {
        long id = physician.getId();
        String activityDescription = "Updated Physician " + physician.getName();
        if (id == 0) {
            id = sequenceGenerator.invoke();
            physician.setId(id);
            activityDescription = "Added Physician "+ physician.getName() ;
        }
        Physician returnValue = physicianRepository.save(physician);
        activityRepository.save(new Activity(DomainType.PHYSICIAN,
                returnValue.getId(),
                new Date(),
                activityDescription,
                returnValue.getGroupId()));
        return returnValue;
    }

    @RequestMapping(value = "physician/{id}", method = RequestMethod.DELETE)
    public Boolean deletePhysician(@PathVariable long id) {
        physicianRepository.delete(id);
        return true;
    }
}

class PhysicianSaveData extends Physician {

    long[] patients;

    public PhysicianSaveData() {
        super();
    }

    public long[] getPatients() {
        return patients;
    }
}
