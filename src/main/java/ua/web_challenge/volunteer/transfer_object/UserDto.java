package ua.web_challenge.volunteer.transfer_object;

/**
 * Created on 29.03.2015
 *
 * @author Bohdan Vanchuhov
 */
public class UserDto {
    private String username;
    private String password;
    private String email;

    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
