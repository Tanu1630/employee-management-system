package SpringBoot_API.SpringBoot_Rest_API.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SpringBoot_API.SpringBoot_Rest_API.model.Employee;
import SpringBoot_API.SpringBoot_Rest_API.serviceIMPL.EmployeeServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

   @Autowired
   private EmployeeServiceImpl empservice;

   @GetMapping("/home")
   public String homePage() {
      return "Welcome to Employee Management";
   }

   @PostMapping("/addEmp")
   public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
      Employee emp = empservice.AddEmployee(employee);

      return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
   }

   @DeleteMapping("/removeEmp/{id}")
   public ResponseEntity<String> removemployee(@PathVariable int id) {

      empservice.removeEmployee(id);

      return new ResponseEntity<String>("Remove Successfully", HttpStatus.ACCEPTED);
   }

   @GetMapping("/findEmp/{id}")
   public ResponseEntity<Optional<Employee>> findEmployeeById(@PathVariable int id) {

      Optional<Employee> emps = empservice.findEmployeeById(id);

      return new ResponseEntity<Optional<Employee>>(emps, HttpStatus.ACCEPTED);
   }

   @GetMapping("/all")
   public ResponseEntity<List<Employee>> listOfEmployees() {

      List<Employee> lEmp = empservice.getAllEmployee();

      return new ResponseEntity<List<Employee>>(lEmp, HttpStatus.ACCEPTED);
   }

   @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        String result = empservice.updateEmployee(id, employee);
        if (result.equals("Update Successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }
}
