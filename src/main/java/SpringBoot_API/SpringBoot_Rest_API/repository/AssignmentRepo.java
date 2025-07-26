package SpringBoot_API.SpringBoot_Rest_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import SpringBoot_API.SpringBoot_Rest_API.model.Assignment;

public interface AssignmentRepo extends JpaRepository<Assignment, Integer> {
    
}
