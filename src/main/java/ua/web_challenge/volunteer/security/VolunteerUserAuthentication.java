package ua.web_challenge.volunteer.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.web_challenge.volunteer.entity.User;

import java.util.Collection;

/**
 * Created on 27.03.2015
 *
 * @author Bohdan Vanchuhov
 */
public class VolunteerUserAuthentication implements Authentication {
    private final UserDetails userDetails;
    private boolean authenticated = true;

    public VolunteerUserAuthentication(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return userDetails.getPassword();
    }

    @Override
    public UserDetails getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        return userDetails.getUsername();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        return userDetails.getUsername();
    }
}
