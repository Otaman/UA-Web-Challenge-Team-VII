package ua.web_challenge.volunteer.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created on 28.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Controller
public class GeneralController {
    @RequestMapping(value = "/api", method = GET)
    public String forwardToApi() {
        return "forward:/static/api.html";
    }

    @RequestMapping(value = "/", method = GET)
    public String forwardToMain() {
        return "forward:/static/main.html";
    }
}
