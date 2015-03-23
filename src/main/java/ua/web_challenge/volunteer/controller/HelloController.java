package ua.web_challenge.volunteer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created on 23.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Controller
public class HelloController {
    @RequestMapping(value = "/hello", method = GET)
    public String hello(Model model) {
        model.addAttribute("message", "hello");
        return "hello";
    }

    @RequestMapping(value = "/", method = GET)
    public String forwardToMain() {
        return "forward:/static/main.html";
    }
}
