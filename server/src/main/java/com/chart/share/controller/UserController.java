package com.chart.share.controller;

import com.chart.share.domain.CSUser;
import com.chart.share.domain.Person;
import com.chart.share.repository.CSUserRepository;
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
    private CSUserRepository userRepository;

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "/{source}/{id}", method = RequestMethod.GET)
    public jsUser getUser(@PathVariable String source, @PathVariable String id) {
        System.out.println("Looking for a user with an id of " + id);
        CSUser csUser = userRepository.findByAuthId(id);
        Person person = null;
        jsUser user = null;
        if (csUser != null) {
            person = personRepository.findOne(csUser.getPersonId());
            if (person != null) {
                user = new jsUser(csUser, person);
            }
        }
        return user;
    }


    @RequestMapping(value = "/{source}/{id}", method = RequestMethod.POST)
    public CSUser insertUser(@PathVariable String source, @PathVariable String id, @RequestBody Person person) {
        CSUser returnUser;
        returnUser = userRepository.findByAuthId(id);
        if (returnUser == null) {
            person = personRepository.save(person);
            returnUser = new CSUser(source, id, person.getId());
            returnUser = userRepository.save(returnUser);
        }
        return returnUser;
    }



    public class jsUser extends CSUser {
        Person person;

        public jsUser(String authSource, String id, long personId, Person person) {
            super(authSource, id, personId);
            this.person = person;
        }

        public jsUser(CSUser user, Person person) {
            super(user.getAuthSource(), user.getAuthId(), user.getPersonId());
            this.person = person;
        }

        public Person getPerson() {
            return person;
        }
    }

    @RequestMapping("/{source}/{id}/seed")
    public String seedData(@PathVariable String source, @PathVariable String id) {

        Person person = new Person("Jenn", "Bennett");
        person = personRepository.save(person);

        CSUser user = new CSUser(source, id, person.getId());

        user = userRepository.save(user);

        return "done";
    }
}
