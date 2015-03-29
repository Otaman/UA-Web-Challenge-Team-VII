package ua.web_challenge.volunteer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.web_challenge.volunteer.transfer.UserDto;
import ua.web_challenge.volunteer.entity.Email;
import ua.web_challenge.volunteer.entity.User;
import ua.web_challenge.volunteer.persistence.EmailDao;
import ua.web_challenge.volunteer.persistence.UserDao;

import static ua.web_challenge.volunteer.entity.UserRole.ROLE_USER;

/**
 * Created on 29.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Service("userService")
public class UserService {
    private UserDao userDao;
    private EmailDao emailDao;

    @Autowired
    public UserService(UserDao userDao, EmailDao emailDao) {
        this.userDao = userDao;
        this.emailDao = emailDao;
    }

    public User addUser(UserDto userDto) {
        User user = buildUser(userDto);

        emailDao.add(user.getEmail());
        userDao.add(user);

        return user;
    }

    private User buildUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        Email email = new Email(userDto.getEmail());
        user.setEmail(email);

        user.addUserRole(ROLE_USER);

        return user;
    }

    public User findByName(String username) {
        return userDao.findByName(username);
    }
}
