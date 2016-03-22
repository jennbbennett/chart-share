package com.chart.share.controller;

import com.chart.share.domain.User;
import com.chart.share.domain.Person;
import com.chart.share.repository.UserRepository;
import com.chart.share.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jenn on 3/13/16.
 */
@RestController
@RequestMapping("/service/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Autowired
    private PersonController personController;

    @Autowired
    private GroupController groupController;

    @RequestMapping(value = "/{source}/{id}", method = RequestMethod.GET)
    public jsUser getUser(@PathVariable String source, @PathVariable String id) {
//        System.out.println("Looking for a user with an id of " + id);
        User user = userRepository.findByAuthId(id);
        Person person = null;
        jsUser jsUser = null;
        if (user != null) {
            person = personRepository.findOne(user.getPersonId());
            if (person != null) {
                jsUser = new jsUser(user, person);
            }
        }
        return jsUser;
    }


    @RequestMapping(value = "/{source}/{id}", method = RequestMethod.POST)
    public jsUser insertUser(@PathVariable String source, @PathVariable String id, @RequestBody Person person) {
        User user;
        user = userRepository.findByAuthId(id);
        if (user == null) {
            person = personController.createPerson(person, 0);
            user = new User(source, id, person.getId());
            user.setId(sequenceGenerator.invoke());
            user = userRepository.save(user);
        } else {
            if(person.getId() != 0) {
                user.setPersonId(person.getId());
                user = userRepository.save(user);
            }
        }
        groupController.findGroup(person.getId());
        jsUser result = new jsUser(user, person);
        return result;
    }



    public class jsUser {
        Person person;
        User user;


        public jsUser(User user, Person person) {
            this.user = user;
            this.person = person;
        }

        public User getUser() {
            return user;
        }

        public Person getPerson() {
            return person;
        }
    }


}
