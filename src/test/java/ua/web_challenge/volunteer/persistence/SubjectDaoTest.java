package ua.web_challenge.volunteer.persistence;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import ua.web_challenge.volunteer.entity.Subject;

import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created on 25.03.2015
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
@DatabaseSetup("/datasets/subject/subject-data.xml")
public class SubjectDaoTest {
    @Autowired
    private SubjectDao subjectDao;

    @Test
    public void findById() throws Exception {
        Subject subject = subjectDao.findById(1);

        assertThat(subject.getName(), is("Education"));
    }

    @Test
    @ExpectedDatabase(value = "/datasets/subject/subject-data-after-insert.xml", assertionMode = NON_STRICT)
    public void addSubject() throws Exception {
        Subject subject = new Subject();
        subject.setName("Community");

        subjectDao.add(subject);
    }

    @Test
    @ExpectedDatabase(value = "/datasets/subject/subject-data-after-update.xml", assertionMode = NON_STRICT)
    public void updateSubject() throws Exception {
        Subject subject = subjectDao.findById(1);
        subject.setName("Education/Teaching");

        subjectDao.update(subject);
    }

    @Test
    @ExpectedDatabase(value = "/datasets/subject/subject-data-after-delete.xml", assertionMode = NON_STRICT)
    public void deleteSubject() throws Exception {
        Subject subject = subjectDao.findById(1);

        subjectDao.delete(subject);
    }

    @Test
    @ExpectedDatabase(value = "/datasets/subject/subject-data-after-delete.xml", assertionMode = NON_STRICT)
    public void deleteSubjectById() throws Exception {
        subjectDao.delete(1);
    }
}
