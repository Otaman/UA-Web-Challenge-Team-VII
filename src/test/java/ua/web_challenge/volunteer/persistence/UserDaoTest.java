package ua.web_challenge.volunteer.persistence;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import ua.web_challenge.volunteer.entity.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static ua.web_challenge.volunteer.entity.UserRole.ROLE_ADMIN;
import static ua.web_challenge.volunteer.entity.UserRole.ROLE_USER;

/**
 * Created on 26.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "file:src/main/webapp/WEB-INF/spring/persistence-context.xml",
        "classpath:/spring/test-mysql-db-properties.xml"
})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("classpath:/datasets/user/user-data.xml")
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void findById() throws Exception {
        User user = userDao.findById(1);

        assertThat(user.getUsername(), is("Bohdan"));
        assertThat(user.getUserRoles(), is(containsInAnyOrder(ROLE_ADMIN, ROLE_USER)));
    }
}
