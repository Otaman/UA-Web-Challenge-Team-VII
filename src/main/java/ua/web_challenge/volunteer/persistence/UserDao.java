package ua.web_challenge.volunteer.persistence;

import ua.web_challenge.volunteer.entity.User;
import ua.web_challenge.volunteer.persistence.generic.GenericDao;

/**
 * Created on 26.03.2015
 *
 * @author Bohdan Vanchuhov
 */
public interface UserDao extends GenericDao<User, Integer> {
    User findByName(String username);
}
