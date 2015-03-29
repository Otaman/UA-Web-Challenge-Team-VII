package ua.web_challenge.volunteer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.web_challenge.volunteer.entity.VolunteerProgram;
import ua.web_challenge.volunteer.persistence.VolunteerProgramDao;

import java.util.List;

/**
 * Created on 29.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Service("volunteerProgramService")
public class VolunteerProgramService {
    @Autowired
    private VolunteerProgramDao volunteerProgramDao;

    public List<VolunteerProgram> findRange(int startPosition, int maxResults) {
        return volunteerProgramDao.findRange(startPosition, maxResults);
    }
}
