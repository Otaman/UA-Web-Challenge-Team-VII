package ua.web_challenge.volunteer.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.web_challenge.volunteer.entity.Email;
import ua.web_challenge.volunteer.entity.VolunteerProgram;
import ua.web_challenge.volunteer.service.VolunteerProgramService;
import ua.web_challenge.volunteer.transfer_object.VolunteerProgramList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static ua.web_challenge.volunteer.controller.rest.HttpConstants.Header.CONTENT_TYPE_JSON;

/**
 * Created on 29.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@RestController()
public class VolunteerProgramController {
    public static final String PROGRAM_PATH = "/api/programs";
    public static final String ADMIN_ADMIN_PATH = "/admin" + PROGRAM_PATH;

    @Autowired
    private VolunteerProgramService volunteerProgramService;

    @RequestMapping(value = PROGRAM_PATH + "/{id}/subscribe", method = GET,
            headers = CONTENT_TYPE_JSON)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void subscribe(@PathVariable("id") int programId) {

    }

    @RequestMapping(value = PROGRAM_PATH + "/{id}/subscribe-guest", method = GET,
            headers = CONTENT_TYPE_JSON)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void subscribeGuest(@PathVariable("id") int programId) {

    }

    @RequestMapping(value = PROGRAM_PATH, method = GET)
    public List<VolunteerProgram> getVolunteerPrograms(
            @RequestParam(value = "offset", defaultValue = "0") int startPosition,
            @RequestParam(value = "quantity", defaultValue = "0") int maxResults) {
        return volunteerProgramService.findRange(startPosition, maxResults);
    }
}
