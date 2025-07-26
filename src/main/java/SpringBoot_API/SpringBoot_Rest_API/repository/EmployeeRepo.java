package SpringBoot_API.SpringBoot_Rest_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import SpringBoot_API.SpringBoot_Rest_API.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee , Integer>{
    
}

