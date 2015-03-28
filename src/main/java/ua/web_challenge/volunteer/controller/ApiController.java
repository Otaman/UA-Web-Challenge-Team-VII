package ua.web_challenge.volunteer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.REException;
import ua.web_challenge.volunteer.entity.User;
import ua.web_challenge.volunteer.persistence.UserDao;
import ua.web_challenge.volunteer.security.VolunteerUserAuthentication;
import ua.web_challenge.volunteer.security.VolunteerUserDetails;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created on 28.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@RestController
public class ApiController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/api/users/current", method = RequestMethod.GET)
    public UserDetails getCurrent() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof VolunteerUserAuthentication) {
            return ((VolunteerUserAuthentication) authentication).getDetails();
        }
        return new VolunteerUserDetails(new User(authentication.getName())); //anonymous user support
    }

    @RequestMapping(value = "/admin/api/users", method = RequestMethod.GET)
    public List<User> list() {
        return userDao.findRange(0,0);
    }

    @RequestMapping(value = "/admin/api", method = RequestMethod.GET)
    public String adminHello() {
        return "Hello";
    }
}
