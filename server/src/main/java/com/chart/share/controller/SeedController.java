package com.chart.share.controller;

import com.chart.share.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jenn on 3/16/16.
 */
@RestController
public class SeedController {

    @Autowired
    private UserController userController;

    @Autowired
    private PersonController personController;

    @Autowired
    private GroupController groupController;

    @Autowired
    private GroupMemberController groupMemberController;

    @RequestMapping("/service/seed")
    public SeedData seedData() {

        Person person = new Person("Jenn", "Bennett",new Address("2512 Bellavista Street","","Castle Rock","CO","80109"));
        person = personController.createPerson(person,0);

        UserController.jsUser user = userController.insertUser("facebook", "10153916257533903", person);

        CSGroup group = groupController.createGroup(new CSGroup(0,"Bennett CSGroup"));

        GroupMember groupMember = groupMemberController.addPersonToGroup(group.getId(), person.getId());

        return new SeedData(person,user,groupController.findGroup(person.getId()));
    }

    class SeedData {
        Person person;
        UserController.jsUser user;
        GroupController.FindGroupResult groupResult;

        public SeedData(Person person, UserController.jsUser user, GroupController.FindGroupResult groupResult) {
            this.person = person;
            this.user = user;
            this.groupResult = groupResult;
        }

        public Person getPerson() {
            return person;
        }

        public UserController.jsUser getUser() {
            return user;
        }

        public GroupController.FindGroupResult getGroupResult() {
            return groupResult;
        }
    }
}
