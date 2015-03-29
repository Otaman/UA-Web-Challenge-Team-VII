package ua.web_challenge.volunteer.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.web_challenge.volunteer.entity.Subject;
import ua.web_challenge.volunteer.persistence.SubjectDao;
import ua.web_challenge.volunteer.persistence.generic.JpaGenericDaoImpl;

/**
 * Created on 25.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Repository("subjectDao")
public class JpaSubjectDao extends JpaGenericDaoImpl<Subject, Integer> implements SubjectDao {
}
