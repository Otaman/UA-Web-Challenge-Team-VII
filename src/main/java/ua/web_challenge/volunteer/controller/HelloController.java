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
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping(method = GET)
    public String hello(Model model) {
        model.addAttribute("message", "hello");
        return "hello";
    }
}
