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

    @RequestMapping(value = "/{source}/{id}", method = RequestMethod.GET)
    public jsUser getUser(@PathVariable String source, @PathVariable String id) {
        System.out.println("Looking for a user with an id of " + id);
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
    public User insertUser(@PathVariable String source, @PathVariable String id, @RequestBody Person person) {
        User returnUser;
        returnUser = userRepository.findByAuthId(id);
        if (returnUser == null) {
            person = personRepository.save(person);
            returnUser = new User(source, id, person.getId());
            returnUser.setId(sequenceGenerator.invoke());
            returnUser = userRepository.save(returnUser);
        } else {
            returnUser.setPersonId(person.getId());
            returnUser = userRepository.save(returnUser);
        }
        return returnUser;
    }



    public class jsUser extends User {
        Person person;

        public jsUser(String authSource, String id, long personId, Person person) {
            super(authSource, id, personId);
            this.person = person;
        }

        public jsUser(User user, Person person) {
            super(user.getAuthSource(), user.getAuthId(), user.getPersonId());
            this.person = person;
        }

        public Person getPerson() {
            return person;
        }
    }


}
