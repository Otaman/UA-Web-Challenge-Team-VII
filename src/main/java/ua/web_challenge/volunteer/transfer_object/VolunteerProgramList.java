package ua.web_challenge.volunteer.transfer_object;

import ua.web_challenge.volunteer.entity.VolunteerProgram;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 29.03.2015
 *
 * @author Bohdan Vanchuhov
 */
public class VolunteerProgramList extends ArrayList<VolunteerProgram> {
    private List<VolunteerProgram> programs;

    public VolunteerProgramList(List<VolunteerProgram> programs) {
        this.programs = programs;
    }

    public List<VolunteerProgram> getPrograms() {
        return programs;
    }

    public void setPrograms(List<VolunteerProgram> programs) {
        this.programs = programs;
    }
}
