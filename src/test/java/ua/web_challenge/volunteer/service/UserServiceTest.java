package ua.web_challenge.volunteer.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import ua.web_challenge.volunteer.transfer_object.UserDto;
import ua.web_challenge.volunteer.entity.User;

import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static ua.web_challenge.volunteer.entity.UserRole.ROLE_ADMIN;
import static ua.web_challenge.volunteer.entity.UserRole.ROLE_USER;

/**
 * Created on 29.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "file:src/main/webapp/WEB-INF/spring/persistence-context.xml",
        "file:src/main/webapp/WEB-INF/spring/service-context.xml",
        "classpath:/spring/test-mysql-db-properties.xml"
})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "classpath:/datasets/user/user-data.xml")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void findByName() throws Exception {
        User user = userService.findByName("Bohdan");

        assertThat(user.getUsername(), is("Bohdan"));
        assertThat(user.getPassword(), is("12345"));
        assertThat(user.getEmail().getValue(), is("bodya.van@gmail.com"));
        assertThat(user.getUserRoles(), is(containsInAnyOrder(ROLE_USER, ROLE_ADMIN)));
    }

    @Test
    @ExpectedDatabase(value = "/datasets/user/user-data-after-insert.xml", assertionMode = NON_STRICT)
    public void addUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("Serhiy");
        userDto.setPassword("0000");
        userDto.setEmail("otaman963@gmail.com");

        userService.addUser(userDto);
    }
}
