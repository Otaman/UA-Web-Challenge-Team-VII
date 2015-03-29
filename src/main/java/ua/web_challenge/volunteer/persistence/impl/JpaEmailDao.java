package ua.web_challenge.volunteer.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.web_challenge.volunteer.entity.Email;
import ua.web_challenge.volunteer.persistence.EmailDao;
import ua.web_challenge.volunteer.persistence.generic.JpaGenericDaoImpl;

/**
 * Created on 29.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Repository("emailDao")
public class JpaEmailDao extends JpaGenericDaoImpl<Email, Integer> implements EmailDao {
}
