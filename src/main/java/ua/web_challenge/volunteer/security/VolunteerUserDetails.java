package ua.web_challenge.volunteer.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.web_challenge.volunteer.entity.User;
import ua.web_challenge.volunteer.entity.UserRole;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 27.03.2015
 *
 * @author Bohdan Vanchuhov
 */
public class VolunteerUserDetails implements UserDetails {
    private User user;

    public VolunteerUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (user.getUserRoles() != null) {
            for (UserRole userRole : user.getUserRoles()) {
                authorities.add(new SimpleGrantedAuthority(userRole.name()));
            }
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isAccountEnabled();
    }
}
