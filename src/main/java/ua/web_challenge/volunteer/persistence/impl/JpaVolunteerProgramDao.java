package ua.web_challenge.volunteer.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.web_challenge.volunteer.entity.VolunteerProgram;
import ua.web_challenge.volunteer.persistence.VolunteerProgramDao;
import ua.web_challenge.volunteer.persistence.generic.JpaGenericDaoImpl;

/**
 * Created on 29.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Repository("volunteerProgramDao")
public class JpaVolunteerProgramDao extends JpaGenericDaoImpl<VolunteerProgram, Integer>
        implements VolunteerProgramDao {
}
