package ua.web_challenge.volunteer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.web_challenge.volunteer.entity.Subject;
import ua.web_challenge.volunteer.persistence.SubjectDao;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created on 24.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Controller
@RequestMapping("/subjects")
public class SubjectController {
    private SubjectDao subjectDao;

    @Autowired
    public SubjectController(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @RequestMapping(value = "/{id}", method = GET, headers = "Accept=application/json")
    @ResponseBody
    public Subject getSubject(@PathVariable("id") int id) {
        Subject subject = subjectDao.findById(id);

        checkSubject(subject);

        return subject;
    }

    private void checkSubject(Subject subject) {
        if (subject == null) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/{id}", method = PUT, headers = "Content-Type=application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSubject(@PathVariable("id") int id, @Valid @RequestBody Subject subject) {
        subjectDao.update(subject);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubject(@PathVariable("id") int id) {
        subjectDao.delete(id);
    }

    @RequestMapping(method = POST, headers = "Content-Type=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Subject createSubject(@Valid @RequestBody Subject subject,
                          BindingResult bindingResult, HttpServletResponse response)
            throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        subjectDao.add(subject);

        setCreatedSubjectLocation(subject, response);

        return subject;
    }

    private void setCreatedSubjectLocation(Subject subject, HttpServletResponse response) {
        response.setHeader("Location", "/subjects/" + subject.getId());
    }
}
