package ua.web_challenge.volunteer.persistence;

import org.springframework.stereotype.Repository;
import ua.web_challenge.volunteer.entity.Subject;
import ua.web_challenge.volunteer.persistence.generic.GenericDao;
import ua.web_challenge.volunteer.persistence.generic.JpaGenericDaoImpl;

/**
 * Created on 24.03.2015
 *
 * @author Bohdan Vanchuhov
 */
public interface SubjectDao extends GenericDao<Subject, Integer> {
}
