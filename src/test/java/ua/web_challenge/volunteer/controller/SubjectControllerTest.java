package ua.web_challenge.volunteer.controller;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created on 24.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({
        "classpath:/spring/test-application-context.xml"
})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("/datasets/subject/subject-data.xml")
public class SubjectControllerTest {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void getSubject() throws Exception {
        mockMvc.perform(get("/subjects/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Education")));
    }

    @Test
    public void getSubject_wrongId() throws Exception {
        mockMvc.perform(get("/subjects/100"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @ExpectedDatabase(value = "/datasets/subject/subject-data-after-update.xml", assertionMode = NON_STRICT)
    public void updateCountry() throws Exception {
        mockMvc.perform(put("/subjects/1")
                        .content("{\"id\":1, \"name\":\"Education/Teaching\"}")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @ExpectedDatabase(value = "/datasets/subject/subject-data-after-delete.xml", assertionMode = NON_STRICT)
    public void deleteSubject() throws Exception {
        mockMvc.perform(delete("/subjects/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @ExpectedDatabase(value = "/datasets/subject/subject-data-after-insert.xml", assertionMode = NON_STRICT)
    public void createSubject() throws Exception {
        mockMvc.perform(post("/subjects")
                        .content("{\"name\":\"Community\"}")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
