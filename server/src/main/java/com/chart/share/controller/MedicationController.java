package com.chart.share.controller;

import com.chart.share.domain.*;
import com.chart.share.repository.*;
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

    @Autowired
    private ActivityRepository activityRepository;

    @RequestMapping(value = "/medication/{id}", method = RequestMethod.GET)
    public MedicationWithPerson getMedication(@PathVariable long id) {
        Medication returnMedication = medicationRepository.findOne(id);
        Person person = null;
        if((returnMedication != null)&&(returnMedication.getPersonId() > 0)) {
                person = personRepository.findOne(returnMedication.getPersonId());
        }
        MedicationWithPerson medicationWithPerson = new MedicationWithPerson(returnMedication,person);

        return medicationWithPerson;
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
        if(groupMedication != null) {
            for (Medication medication : groupMedication) {
                Physician physician = physicianRepository.findOne(medication.getPhysicianId());
                Person person = personRepository.findOne(medication.getPersonId());
                MedicationListItem item = new MedicationListItem(medication.getId(), medication.getRxName(), medication.getRefills());

                if (person != null) {
                    item.setPatientName(person.getName());
                }
                if (physician != null) {
                    item.setPhysicianName(physician.getName());
                }


                results.add(item);
            }
        }
        return results;
    }

    class MedicationWithPerson {
        Medication medication;
        Person person;

        public MedicationWithPerson(Medication medication, Person person) {
            this.medication = medication;
            this.person = person;
        }

        public Medication getMedication() {
            return medication;
        }

        public Person getPerson() {
            return person;
        }
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

    @RequestMapping(value = "/medication", method = RequestMethod.PUT)
    public Medication updateMedication(@RequestBody Medication medication){
        return createMedication(medication);
//        long id = medication.getId();
//        String activityDescription = "Updated Medication";
//
//        if(id ==0) {
//            id = sequenceGenerator.invoke();
//            medication.setId(id);
//            activityDescription = "Added Medication";
//
//        }
//
//        medication =  medicationRepository.save(medication);
//        activityRepository.save(new Activity(DomainType.MEDICATION,
//                medication.getId(),
//                new Date(),
//                activityDescription,
//                medication.getGroupId()));
//
//
//        return medication;
    }


    @RequestMapping(value = "/medication", method = RequestMethod.POST)
    public Medication createMedication(@RequestBody Medication medication) {
        long id = medication.getId();
        String activityDescription = "Updated Medication";
        if(id == 0) {
            id = sequenceGenerator.invoke();
            medication.setId(id);
            activityDescription = "Added Medication";
        }
        medication = medicationRepository.save(medication);
        activityRepository.save(new Activity(DomainType.MEDICATION,
                medication.getId(),
                new Date(),
                activityDescription,
                medication.getGroupId()));
        return medication;
    }


    @RequestMapping(value = "medication/{id}", method = RequestMethod.DELETE)
    public Boolean deleteMedication(@PathVariable long id){
        medicationRepository.delete(id);
        return true;
    }


}

class MedicationSaveData  {

    Medication medication;
    Person[] patientPersons;
    long [] physicians;

    public MedicationSaveData() {
    }

    public MedicationSaveData(Medication medication, Person[] patientPersons) {
        this.medication = medication;
        this.patientPersons = patientPersons;
    }

    public Medication getMedication() {
        return medication;
    }

    public Person[] getPatientPersons() {
        return patientPersons;
    }
}

