package ua.web_challenge.volunteer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.web_challenge.volunteer.entity.User;
import ua.web_challenge.volunteer.entity.UserRole;
import ua.web_challenge.volunteer.persistence.UserDao;
import ua.web_challenge.volunteer.security.VolunteerUserDetails;

import java.util.HashSet;
import java.util.Set;

/**
 * Created on 26.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByName(username);
        checkUserExist(user);

        UserDetails userDetails = new VolunteerUserDetails(user);
        detailsChecker.check(userDetails);

        return userDetails;
    }

    private void checkUserExist(User user) {
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
