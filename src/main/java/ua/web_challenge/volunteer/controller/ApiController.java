package ua.web_challenge.volunteer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Serhiy on 3/27/2015.
 */
@Controller
public class ApiController {
    @RequestMapping(value = "/api", method = GET)
    public String forwardToApi() {
        return "forward:/static/api.html";
    }
}
