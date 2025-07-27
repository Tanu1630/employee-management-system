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
import org.springframework.web.bind.annotation.PatchMapping;

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
      String result = empservice.removeEmployee(id);
      if (result.startsWith("Delete")) {
         return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
      } else {
         return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
      }
   }

   @GetMapping("/findEmp/{id}")
   public ResponseEntity<?> findEmployeeById(@PathVariable int id) {
      Optional<Employee> emps = empservice.findEmployeeById(id);
      if (emps.isPresent()) {
         return new ResponseEntity<>(emps, HttpStatus.OK);
      } else {
         return new ResponseEntity<>("Employee with ID " + id + " not found", HttpStatus.NOT_FOUND);
      }
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

   @PatchMapping("/updatePartial/{id}")
   public ResponseEntity<String> partialUpdateEmployee(@PathVariable int id, @RequestBody Employee employee) {
      String result = empservice.partialUpdateEmployee(id, employee);
      if (result.equals("Partial update successful")) {
         return ResponseEntity.ok(result);
      } else {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
      }
   }

}
