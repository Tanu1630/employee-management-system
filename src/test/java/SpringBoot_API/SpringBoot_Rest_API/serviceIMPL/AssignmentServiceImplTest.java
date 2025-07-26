package SpringBoot_API.SpringBoot_Rest_API.serviceIMPL;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import SpringBoot_API.SpringBoot_Rest_API.model.Assignment;
import SpringBoot_API.SpringBoot_Rest_API.model.Employee;
import SpringBoot_API.SpringBoot_Rest_API.repository.AssignmentRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AssignmentServiceImplTest {

    @Mock
    private AssignmentRepo assignmentRepo;

    @InjectMocks
    private AssignmentServiceImpl assignmentService;

    private Assignment sampleAssignment;
    private Employee sampleEmployee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleEmployee = new Employee(1, "John Doe", 25, "California", "FullTime", 50000);
        sampleAssignment = new Assignment();
        sampleAssignment.setAssignmentId(1);
        sampleAssignment.setEmployee(sampleEmployee);
        sampleAssignment.setProjectName("Project X");
        sampleAssignment.setTeamName("Team Alpha");
        sampleAssignment.setPosition("Developer");
        sampleAssignment.setDurationInMonths(12);
    }

    @Test
    void testAddAssignment() {
        when(assignmentRepo.save(sampleAssignment)).thenReturn(sampleAssignment);
        Assignment saved = assignmentService.addAssignment(sampleAssignment);
        assertNotNull(saved);
        assertEquals(sampleAssignment.getProjectName(), saved.getProjectName());
        verify(assignmentRepo, times(1)).save(sampleAssignment);
    }

    @Test
    void testRemoveAssignment() {
        doNothing().when(assignmentRepo).deleteById(1);
        String response = assignmentService.removeAssignment(1);
        assertEquals("Assignment removed successfully", response);
        verify(assignmentRepo, times(1)).deleteById(1);
    }

    @Test
    void testFindAssignmentById_Found() {
        when(assignmentRepo.findById(1)).thenReturn(Optional.of(sampleAssignment));
        Optional<Assignment> found = assignmentService.findAssignmentById(1);
        assertTrue(found.isPresent());
        assertEquals(sampleAssignment.getAssignmentId(), found.get().getAssignmentId());
    }

    @Test
    void testFindAssignmentById_NotFound() {
        when(assignmentRepo.findById(2)).thenReturn(Optional.empty());
        Optional<Assignment> found = assignmentService.findAssignmentById(2);
        assertFalse(found.isPresent());
    }

    @Test
    void testGetAllAssignments() {
        when(assignmentRepo.findAll()).thenReturn(Arrays.asList(sampleAssignment));
        List<Assignment> assignments = assignmentService.getAllAssignments();
        assertEquals(1, assignments.size());
        verify(assignmentRepo, times(1)).findAll();
    }

    @Test
    void testUpdateAssignment_Found() {
        Assignment updated = new Assignment();
        updated.setProjectName("Updated Project");
        updated.setTeamName("Updated Team");
        updated.setPosition("Lead");
        updated.setDurationInMonths(6);
        updated.setEmployee(sampleEmployee);

        when(assignmentRepo.findById(1)).thenReturn(Optional.of(sampleAssignment));
        when(assignmentRepo.save(any(Assignment.class))).thenReturn(updated);

        String result = assignmentService.updateAssignment(1, updated);
        assertEquals("Assignment updated successfully", result);
        verify(assignmentRepo, times(1)).save(updated);
    }

    @Test
    void testUpdateAssignment_NotFound() {
        when(assignmentRepo.findById(2)).thenReturn(Optional.empty());

        String result = assignmentService.updateAssignment(2, sampleAssignment);
        assertEquals("Assignment not found", result);
        verify(assignmentRepo, never()).save(any());
    }
}
