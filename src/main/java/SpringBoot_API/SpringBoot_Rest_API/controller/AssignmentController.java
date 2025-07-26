package SpringBoot_API.SpringBoot_Rest_API.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import SpringBoot_API.SpringBoot_Rest_API.model.Assignment;
import SpringBoot_API.SpringBoot_Rest_API.serviceIMPL.AssignmentServiceImpl;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {

    @Autowired
    private AssignmentServiceImpl assignmentService;

    @PostMapping("/add")
    public ResponseEntity<Assignment> addAssignment(@RequestBody Assignment assignment) {
        
        Assignment addedAssignment = assignmentService.addAssignment(assignment);

        return new ResponseEntity<>(addedAssignment, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Assignment>> findAssignment(@PathVariable int id) {
     
        Optional<Assignment> assignment = assignmentService.findAssignmentById(id);

        return new ResponseEntity<>(assignment, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeAssignment(@PathVariable int id) {
      
        String result = assignmentService.removeAssignment(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Assignment>> getAllAssignments() {
      
        List<Assignment> assignments = assignmentService.getAllAssignments();

        return new ResponseEntity<>(assignments, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAssignment(@PathVariable int id, @RequestBody Assignment assignment) {
        
        String result = assignmentService.updateAssignment(id, assignment);
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
