package SpringBoot_API.SpringBoot_Rest_API.serviceIMPL;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpringBoot_API.SpringBoot_Rest_API.model.Assignment;
import SpringBoot_API.SpringBoot_Rest_API.repository.AssignmentRepo;
import SpringBoot_API.SpringBoot_Rest_API.service.AssignmentService;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    private AssignmentRepo assignmentRepo;

    @Override
    public Assignment addAssignment(Assignment assignment) {
        return assignmentRepo.save(assignment);
    }

    @Override
    public String removeAssignment(int id) {
        assignmentRepo.deleteById(id);
        return "Assignment removed successfully";
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
}
