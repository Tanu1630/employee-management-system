package SpringBoot_API.SpringBoot_Rest_API.controller;

import SpringBoot_API.SpringBoot_Rest_API.model.Assignment;
import SpringBoot_API.SpringBoot_Rest_API.model.Employee;
import SpringBoot_API.SpringBoot_Rest_API.serviceIMPL.AssignmentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AssignmentController.class)
public class AssignmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AssignmentServiceImpl assignmentService;

    @Autowired
    private ObjectMapper objectMapper;

    private Assignment getSampleAssignment() {
        Employee employee = new Employee(1, "John Doe", 25, "NY", "Full-time", 50000);
        Assignment assignment = new Assignment();
        assignment.setAssignmentId(1);
        assignment.setEmployee(employee);
        assignment.setProjectName("ProjectX");
        assignment.setTeamName("Alpha");
        assignment.setPosition("Developer");
        assignment.setDurationInMonths(12);
        return assignment;
    }

    @Test
    public void testAddAssignment() throws Exception {
        Assignment assignment = getSampleAssignment();

        when(assignmentService.addAssignment(any(Assignment.class))).thenReturn(assignment);

        mockMvc.perform(post("/assignment/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(assignment)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.assignmentId").value(1))
                .andExpect(jsonPath("$.projectName").value("ProjectX"));
    }

 @Test
public void testFindAssignment() throws Exception {
    Assignment assignment = getSampleAssignment();

    when(assignmentService.findAssignmentById(1)).thenReturn(Optional.of(assignment));

    mockMvc.perform(get("/assignment/find/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.assignmentId").value(1));
}


    @Test
    public void testGetAllAssignments() throws Exception {
        Assignment assignment1 = getSampleAssignment();
        Assignment assignment2 = getSampleAssignment();
        assignment2.setAssignmentId(2);
        assignment2.setProjectName("ProjectY");

        when(assignmentService.getAllAssignments()).thenReturn(Arrays.asList(assignment1, assignment2));

        mockMvc.perform(get("/assignment/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void testRemoveAssignment() throws Exception {
        when(assignmentService.removeAssignment(1)).thenReturn("Assignment removed successfully");

        mockMvc.perform(delete("/assignment/remove/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Assignment removed successfully"));
    }

    @Test
    public void testUpdateAssignment() throws Exception {
        when(assignmentService.updateAssignment(Mockito.eq(1), any(Assignment.class)))
                .thenReturn("Assignment updated successfully");

        mockMvc.perform(put("/assignment/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getSampleAssignment())))
                .andExpect(status().isOk())
                .andExpect(content().string("Assignment updated successfully"));
    }
}
