package ua.web_challenge.volunteer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Serhiy on 3/29/2015.
 */
@Controller
public class AdminController {
    @RequestMapping(value = "/administration", method = GET)
    public String forwardToAdmin(){
        return "forward:/static/administration.html";
    }
}
