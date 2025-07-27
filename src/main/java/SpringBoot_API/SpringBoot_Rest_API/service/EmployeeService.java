package SpringBoot_API.SpringBoot_Rest_API.service;

import java.util.List;
import java.util.Optional;

import SpringBoot_API.SpringBoot_Rest_API.model.Employee;

public interface EmployeeService {
     
    public Employee AddEmployee(Employee employee);
    
    public String removeEmployee(int id);

    public Optional<Employee> findEmployeeById(int id);

    public List<Employee> getAllEmployee();
    
    public String updateEmployee(int id , Employee updatedEmp);

    public String partialUpdateEmployee(int id, Employee updatedEmp);

    
}
