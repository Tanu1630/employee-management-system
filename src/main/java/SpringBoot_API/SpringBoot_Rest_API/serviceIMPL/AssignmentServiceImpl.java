package SpringBoot_API.SpringBoot_Rest_API.serviceIMPL;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpringBoot_API.SpringBoot_Rest_API.model.Assignment;
import SpringBoot_API.SpringBoot_Rest_API.model.Employee;
import SpringBoot_API.SpringBoot_Rest_API.repository.AssignmentRepo;
import SpringBoot_API.SpringBoot_Rest_API.repository.EmployeeRepo;
import SpringBoot_API.SpringBoot_Rest_API.service.AssignmentService;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    private AssignmentRepo assignmentRepo;

    @Autowired
    private EmployeeRepo employeeRepo;


   @Override
    public Assignment addAssignment(Assignment assignment) {
        int employeeId = assignment.getEmployee().getId();
        Optional<Employee> employeeOptional = employeeRepo.findById(employeeId); // ✅ corrected this line

        if (employeeOptional.isPresent()) {
            assignment.setEmployee(employeeOptional.get()); // Set full employee entity
            return assignmentRepo.save(assignment);
        } else {
            throw new RuntimeException("Employee with ID " + employeeId + " does not exist");
        }
    }

    @Override
    public String removeAssignment(int id) {
        if (assignmentRepo.existsById(id)) {
            assignmentRepo.deleteById(id);
            return "Assignment removed successfully";
        } else {
            return "Assignment not found";
        }
    }

    @Override
    public Optional<Assignment> findAssignmentById(int id) {
        return assignmentRepo.findById(id);
    }

    @Override
    public List<Assignment> getAllAssignments() {
        return assignmentRepo.findAll();
    }

   @Override
public String updateAssignment(int id, Assignment updatedAssignment) {
    Optional<Assignment> optionalAssignment = assignmentRepo.findById(id);
    if (optionalAssignment.isPresent()) {
        updatedAssignment.setAssignmentId(id);
        assignmentRepo.save(updatedAssignment);
        return "Assignment updated successfully";
    } else {
        return "Assignment not found";
    }
}

@Override
public String partialUpdateAssignment(int id, Assignment updatedAssignment) {
    Optional<Assignment> optionalAssignment = assignmentRepo.findById(id);
    if (optionalAssignment.isPresent()) {
        Assignment existingAssignment = optionalAssignment.get();

        if (updatedAssignment.getEmployee() != null) {
            existingAssignment.setEmployee(updatedAssignment.getEmployee());
        }
        if (updatedAssignment.getProjectName() != null) {
            existingAssignment.setProjectName(updatedAssignment.getProjectName());
        }
        if (updatedAssignment.getTeamName() != null) {
            existingAssignment.setTeamName(updatedAssignment.getTeamName());
        }
        if (updatedAssignment.getPosition() != null) {
            existingAssignment.setPosition(updatedAssignment.getPosition());
        }
        if (updatedAssignment.getDurationInMonths() != 0) {
            existingAssignment.setDurationInMonths(updatedAssignment.getDurationInMonths());
        }

        assignmentRepo.save(existingAssignment);
        return "Partial update successful";
    } else {
        return "Assignment not found";
    }
}

}
