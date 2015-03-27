package ua.web_challenge.volunteer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.web_challenge.volunteer.entity.User;
import ua.web_challenge.volunteer.entity.UserRole;
import ua.web_challenge.volunteer.persistence.UserDao;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByName(username);

        return buildUserDetails(user);
    }

    private UserDetails buildUserDetails(User user) {
        Set<GrantedAuthority> authorities = buildAuthoritiesSet(user);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }

    private Set<GrantedAuthority> buildAuthoritiesSet(User user) {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (UserRole userRole : user.getUserRoles()) {
            roles.add(new SimpleGrantedAuthority(userRole.name()));
        }
        return roles;
    }
}
