package SpringBoot_API.SpringBoot_Rest_API.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import SpringBoot_API.SpringBoot_Rest_API.model.Assignment;
import SpringBoot_API.SpringBoot_Rest_API.serviceIMPL.AssignmentServiceImpl;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {

    @Autowired
    private AssignmentServiceImpl assignmentService;

      @PostMapping("/add")
    public ResponseEntity<?> addAssignment(@RequestBody Assignment assignment) {
        try {
            Assignment addedAssignment = assignmentService.addAssignment(assignment);
            return new ResponseEntity<>(addedAssignment, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); 
        }
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<?> findAssignment(@PathVariable int id) {
        Optional<Assignment> assignment = assignmentService.findAssignmentById(id);
        if (assignment.isPresent()) {
            return new ResponseEntity<>(assignment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Assignment not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeAssignment(@PathVariable int id) {
        String result = assignmentService.removeAssignment(id);
        if (result.equals("Assignment removed successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Assignment>> getAllAssignments() {
        List<Assignment> assignments = assignmentService.getAllAssignments();
        return new ResponseEntity<>(assignments, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAssignment(@PathVariable int id, @RequestBody Assignment assignment) {
        String result = assignmentService.updateAssignment(id, assignment);
        if (result.equals("Assignment updated successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/partialupdate/{id}")
    public ResponseEntity<String> partialUpdateAssignment(@PathVariable int id, @RequestBody Assignment assignment) {
        String result = assignmentService.partialUpdateAssignment(id, assignment);
        if (result.equals("Partial update successful")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }
}
