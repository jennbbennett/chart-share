package com.chart.share.security;

import com.chart.share.domain.Person;
import com.chart.share.domain.User;
import com.chart.share.repository.PersonRepository;
import com.chart.share.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private PersonRepository personRepository;

    @RequestMapping({"/user", "/me"})
    public Map<String, Object> user(Principal principal,HttpServletRequest request) {
        Map<String, Object> map = new LinkedHashMap<>();
//        log.info("In user function");
        String principalName = null;
        String source = null;
        boolean isFound = false;
        if (principal != null) {
            isFound = true;
            principalName = principal.getName();
            source = "facebook";

        } else {
            principalName = (String)request.getSession().getAttribute(fakeKey);
            if(principalName != null && principalName.length() > 2) {
                isFound = true;
                // this is for the fake login
                source = "facebook";
            }
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
                }
            }
        }
        return map;
    }


    // methods to fake the login for now

    @RequestMapping("/login/facebook")
    public void fakeFacebookLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute(fakeKey,fakeId);
        System.out.println("Fake Login");
        response.sendRedirect("/");
    }


    @RequestMapping("/logout")
    public Boolean fakeLogout(HttpServletRequest request){
        request.getSession().setAttribute(fakeKey,null);
        return true;
    }

}
