package ua.web_challenge.volunteer.controller.web;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created on 26.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Controller
public class AuthenticationController {
    @RequestMapping(value = "/welcome**", method = GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Volunteer Programs!!!");
        model.addAttribute("message", "This is welcome page");

        return "welcome";
    }

    @RequestMapping(value = "/admin", method = GET)
    public String adminPage(Model model) {
        model.addAttribute("title", "Volunteer Admin Page");
        model.addAttribute("message", "This is admin page");

        return "admin";
    }

    @RequestMapping(value = "/login", method = GET)
    public String forwardToLogin() {
        return "forward:/static/login.html";
    }

    @RequestMapping(value = "/registration", method = GET)
    public String forwardToRegistration() {
        return "forward:/static/registration.html";
    }

    @RequestMapping(value = "/restore", method = GET)
    public String forwardToRestore() {
        return "forward:/static/restore.html";
    }
    /*@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              HttpServletRequest request,
                              Model model) {
        if (error != null) {
            model.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }

        return "login";
    }*/

    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password!";
        }

        return error;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model) {
        // check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addAttribute("username", userDetail.getUsername());

        }

        return "403";
    }
}
