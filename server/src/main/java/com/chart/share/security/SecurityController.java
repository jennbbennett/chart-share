package com.chart.share.security;

import com.chart.share.domain.CSGroup;
import com.chart.share.domain.GroupMember;
import com.chart.share.domain.Person;
import com.chart.share.domain.User;
import com.chart.share.repository.GroupMemberRepository;
import com.chart.share.repository.GroupRepository;
import com.chart.share.repository.PersonRepository;
import com.chart.share.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
public class SecurityController {

    Logger log = LoggerFactory.getLogger(SecurityController.class);

    private static final String fakeId = "10153916257533903";
    private static final String fakeKey = "authedUser";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/user")
    public Map<String, Object> user(Principal principal,HttpServletRequest request) {
        Map<String, Object> map = new LinkedHashMap<>();
//        log.info("In user function");
        String principalName = null;
        String source = null;
        log.warn("in /user request");
        boolean isFound = false;
        if (principal != null) {
            log.warn("Principal is not null");
            isFound = true;
            principalName = principal.getName();

            map.put("principal",principal);
            source = "facebook";

        } else {
            log.warn("Principal is null");
//            principalName = (String)request.getSession().getAttribute(fakeKey);
//            if(principalName != null && principalName.length() > 2) {
//                isFound = true;
//                // this is for the fake login
//                source = "facebook";
//            }
        }
        if(isFound){
            map.put("principalName", principalName);
//            map.put("source", source);
            User user = userRepository.findByAuthId(principalName);

            map.put("authenticated", true);
            map.put("user",user);
            Person person = null;
            if (user != null) {
                person = personRepository.findOne(user.getPersonId());
                if (person != null) {
                    map.put("person",person);
                    GroupMember groupMember = groupMemberRepository.findByPersonId(person.getId());
                    if(groupMember != null) {
                        CSGroup group = groupRepository.findOne(groupMember.getGroupId());
                        if (group != null) {
                            map.put("group", group);
                        }
                    }
                }
            }
        }
        return map;
    }


    // methods to fake the login for now

//    @RequestMapping("/login/facebook")
//    public void fakeFacebookLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        request.getSession().setAttribute(fakeKey,fakeId);
//        System.out.println("Fake Login");
//        response.sendRedirect("/");
//    }
//
//
//    @RequestMapping("/logout")
//    public Boolean fakeLogout(HttpServletRequest request){
//        request.getSession().setAttribute(fakeKey,null);
//        return true;
//    }

}
