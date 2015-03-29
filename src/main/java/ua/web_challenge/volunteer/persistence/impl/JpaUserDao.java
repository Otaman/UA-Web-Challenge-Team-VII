package ua.web_challenge.volunteer.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.web_challenge.volunteer.entity.User;
import ua.web_challenge.volunteer.persistence.UserDao;
import ua.web_challenge.volunteer.persistence.generic.JpaGenericDaoImpl;

/**
 * Created on 26.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Repository("userDao")
public class JpaUserDao extends JpaGenericDaoImpl<User, Integer> implements UserDao {
    @Override
    public User findByName(String username) {
        return entityManager.createNamedQuery("User.findByName", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
