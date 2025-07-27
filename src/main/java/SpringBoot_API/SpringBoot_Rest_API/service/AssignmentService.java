package SpringBoot_API.SpringBoot_Rest_API.service;

import java.util.List;
import java.util.Optional;

import SpringBoot_API.SpringBoot_Rest_API.model.Assignment;

public interface AssignmentService {

    public Assignment addAssignment(Assignment assignment);

    public String removeAssignment(int id);

    public Optional<Assignment> findAssignmentById(int id);

    public List<Assignment> getAllAssignments();

    public String updateAssignment(int id, Assignment updatedAssignment);

    public  String partialUpdateAssignment(int id, Assignment updatedAssignment);
}
