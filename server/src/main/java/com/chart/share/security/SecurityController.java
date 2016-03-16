package com.chart.share.security;

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

    private static final String fakeId = "10153916257533903";
    private static final String fakeKey = "authedUser";

    @RequestMapping({"/user", "/me"})
    public Map<String, String> user(Principal principal,HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        if (principal != null) {
            map.put("name", principal.getName());
            map.put("source", "facebook");
        } else {
            String id = (String)request.getSession().getAttribute(fakeKey);
            if(id != null && id.length() > 2) {
                // this is for the fake login
                map.put("name", id);
                map.put("source", "facebook");
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
