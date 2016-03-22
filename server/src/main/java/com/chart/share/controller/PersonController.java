package com.chart.share.controller;

import com.chart.share.domain.Activity;
import com.chart.share.domain.DomainType;
import com.chart.share.domain.Person;
import com.chart.share.domain.Sequence;
import com.chart.share.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by jenn on 3/15/16.
 */

@RestController
@RequestMapping("/service")
public class PersonController {


    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private GroupMemberController groupMemberController;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Autowired
    private ActivityRepository activityRepository;

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable long id) {
        Person returnPerson;
        returnPerson = personRepository.findOne(id);
        return returnPerson;
    }

    @RequestMapping(value = "/person", method = RequestMethod.PUT)
    public Person updatePerson(@RequestBody Person person){
        long id = person.getId();
        String activityDescription = "Updated Person";

        if(id ==0) {
            id = sequenceGenerator.invoke();
            person.setId(id);
            activityDescription = "Added Person";

        }

        person =  personRepository.save(person);
        activityRepository.save(new Activity(DomainType.PERSON,
                person.getId(),
                new Date(),
                activityDescription,
                person.getGroupId()));
        return person;
    }



    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public Person createPerson(@RequestBody Person person, @RequestParam(defaultValue = "0") long groupId) {
        long id = person.getId();
        String activityDescription = "Updated Person";
        if (id == 0) {
            id = sequenceGenerator.invoke();
            person.setId(id);
            activityDescription = "Added Person";
        }
        person = personRepository.save(person);
        if(groupId > 0){
            groupMemberController.addPersonToGroup(groupId, person.getId());
        }
        activityRepository.save(new Activity(DomainType.PERSON,
                person.getId(),
                new Date(),
                activityDescription,
                person.getGroupId()));
        return person;
    }


    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    public Boolean deletePerson(@PathVariable long id) {
        personRepository.delete(id);
        return true;
    }

}
