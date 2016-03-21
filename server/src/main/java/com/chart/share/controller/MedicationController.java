package com.chart.share.controller;

import com.chart.share.domain.*;
import com.chart.share.repository.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PatientRepository patientRepository;

    @RequestMapping(value = "/medication/{id}", method = RequestMethod.GET)
    public Medication getMedication(@PathVariable long id) {
        Medication returnMedication;
        returnMedication = medicationRepository.findOne(id);
        return returnMedication;
    }

//    @RequestMapping(value = "/medication/patients/{physicianId}", method = RequestMethod.GET)
//    public List<PatientResult> getPatientsByPhysicianId(@PathVariable long physicianId){
//        List<Patient> patients;
//        List<PatientResult> results = new LinkedList<>();
//
//        patients = patientRepository.findByPhysicianId(physicianId);
//
//        for(Patient patient : patients){
//            Person person = personRepository.findOne(patient.getPersonId());
//            results.add(new PatientResult(person, patient.getDateAdded()));
//        }
//        return results;
//    }

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


    @RequestMapping(value = "/medication", method = RequestMethod.GET)
    public List<MedicationListItem> getGroupMedications(@RequestParam long personId){
        List<Medication> groupMedication = null;
        List<MedicationListItem> results = new LinkedList<>();
        GroupMember groupMember = groupMemberRepository.findByPersonId(personId);
        if(groupMember != null){
            long groupId = groupMember.getGroupId();
            groupMedication = medicationRepository.findByGroupId(groupId);
        }
        for(Medication medication: groupMedication){
            Physician physician = physicianRepository.findOne(medication.getPhysicianId());
            Person person = personRepository.findOne(medication.getPersonId());
            MedicationListItem item = new MedicationListItem(medication.getId(),medication.getRxName(),medication.getRefills());

            if(person != null){
                item.setPatientName(person.getName());
            }
            if(physician != null){
                item.setPhysicianName(physician.getName());
            }


            results.add(item);
        }
        return results;
    }

    class MedicationListItem {
        long medicationId;
        String rxName;
        String patientName;
        String physicianName;
        String refills;

        public MedicationListItem() {
        }

        public MedicationListItem(long medicationId, String rxName, String refills) {
            this.medicationId = medicationId;
            this.rxName = rxName;
            this.refills = refills;
        }

        public long getMedicationId() {
            return medicationId;
        }

        public void setMedicationId(long medicationId) {
            this.medicationId = medicationId;
        }

        public String getRxName() {
            return rxName;
        }

        public void setRxName(String rxName) {
            this.rxName = rxName;
        }

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public String getPhysicianName() {
            return physicianName;
        }

        public void setPhysicianName(String physicianName) {
            this.physicianName = physicianName;
        }

        public String getRefills() {
            return refills;
        }

        public void setRefills(String refills) {
            this.refills = refills;
        }
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
