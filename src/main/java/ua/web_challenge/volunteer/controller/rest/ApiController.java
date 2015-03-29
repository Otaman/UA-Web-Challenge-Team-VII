package ua.web_challenge.volunteer.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.web_challenge.volunteer.entity.User;
import ua.web_challenge.volunteer.persistence.UserDao;
import ua.web_challenge.volunteer.security.VolunteerUserAuthentication;
import ua.web_challenge.volunteer.security.VolunteerUserDetails;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static ua.web_challenge.volunteer.controller.rest.HttpConstants.Header.CONTENT_TYPE_JSON;

/**
 * Created on 28.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@RestController
public class ApiController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/api/registration", method = POST, headers = CONTENT_TYPE_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public User registrateUser(@Valid @RequestBody User user,
                                  BindingResult bindingResult, HttpServletResponse response)
            throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        user = userDao.add(user);

//        setCreatedSubjectLocation(subject, response);

        return user;
    }



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

    @RequestMapping(value = "/a", method = GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public String aHello() {
        return "User";
    }
}
