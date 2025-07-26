package SpringBoot_API.SpringBoot_Rest_API.serviceIMPL;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpringBoot_API.SpringBoot_Rest_API.model.Employee;
import SpringBoot_API.SpringBoot_Rest_API.repository.EmployeeRepo;
import SpringBoot_API.SpringBoot_Rest_API.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public Employee AddEmployee(Employee employee) {
         
        Employee emp = employeeRepo.save(employee);
        return emp;
    }

    @Override
    public String removeEmployee(int id) {
        
         employeeRepo.deleteById(id);
         return "Delete data successfully";
    }

    @Override
    public Optional<Employee> findEmployeeById(int id) {
         
        Optional<Employee> emp =  employeeRepo.findById(id);
        
        if(emp.isPresent()){
            return emp;
        }else{
             return null;
        }
       
    }

    @Override
    public List<Employee> getAllEmployee() {
         
          List<Employee> empList = employeeRepo.findAll();
          return empList;
    }

    // @Override
    // public String updateEmployee(int id) {
         
    //   Optional<Employee> emp =  employeeRepo.findById(id);
    //   if(emp.isPresent())
    //   {
    //      Employee emps = new Employee();

    //      employeeRepo.save(emps);

    //      return "Update Successfully";
    //   }else{
    //        return "Employee not found";
    //   }

    // }

    @Override
    public String updateEmployee(int id, Employee updatedEmp) {
        Optional<Employee> empOptional = employeeRepo.findById(id);
        if (empOptional.isPresent()) {
            Employee existingEmp = empOptional.get();

            existingEmp.setName(updatedEmp.getName());
            existingEmp.setAge(updatedEmp.getAge());
            existingEmp.setState(updatedEmp.getState());
            existingEmp.setType(updatedEmp.getType());
            existingEmp.setSalary(updatedEmp.getSalary());

            employeeRepo.save(existingEmp);

            return "Update Successfully";
        } else {
            return "Employee not found";
        }
    }
     
}
