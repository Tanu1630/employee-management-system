package SpringBoot_API.SpringBoot_Rest_API.serviceIMPL;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import SpringBoot_API.SpringBoot_Rest_API.model.Employee;
import SpringBoot_API.SpringBoot_Rest_API.repository.EmployeeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = new Employee(1, "John Doe", 25, "California", "Full-time", 50000);
    }

    @Test
    public void testAddEmployee() {
        when(employeeRepo.save(employee)).thenReturn(employee);
        Employee savedEmp = employeeService.AddEmployee(employee);
        assertNotNull(savedEmp);
        assertEquals("John Doe", savedEmp.getName());
        verify(employeeRepo, times(1)).save(employee);
    }

    @Test
    public void testRemoveEmployee_Found() {
        when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepo).deleteById(1);
        String result = employeeService.removeEmployee(1);
        assertEquals("Delete data successfully", result);
        verify(employeeRepo, times(1)).findById(1);
        verify(employeeRepo, times(1)).deleteById(1);
    }

    @Test
    public void testRemoveEmployee_NotFound() {
        when(employeeRepo.findById(1)).thenReturn(Optional.empty());
        String result = employeeService.removeEmployee(1);
        assertEquals("Employee with ID 1 not found", result);
        verify(employeeRepo, times(1)).findById(1);
        verify(employeeRepo, never()).deleteById(anyInt());
    }

    @Test
    public void testFindEmployeeById_Found() {
        when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
        Optional<Employee> foundEmp = employeeService.findEmployeeById(1);
        assertTrue(foundEmp.isPresent());
        assertEquals("John Doe", foundEmp.get().getName());
        verify(employeeRepo, times(1)).findById(1);
    }

    @Test
    public void testFindEmployeeById_NotFound() {
        when(employeeRepo.findById(1)).thenReturn(Optional.empty());
        Optional<Employee> foundEmp = employeeService.findEmployeeById(1);
        assertFalse(foundEmp.isPresent()); // Corrected from assertNull to assertFalse
        verify(employeeRepo, times(1)).findById(1);
    }

    @Test
    public void testGetAllEmployee() {
        List<Employee> employees = Arrays.asList(
                employee,
                new Employee(2, "Jane", 30, "Texas", "Part-time", 40000)
        );
        when(employeeRepo.findAll()).thenReturn(employees);
        List<Employee> empList = employeeService.getAllEmployee();
        assertEquals(2, empList.size());
        verify(employeeRepo, times(1)).findAll();
    }

    @Test
    public void testUpdateEmployee_Found() {
        when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
        when(employeeRepo.save(any(Employee.class))).thenReturn(employee);
        Employee updated = new Employee(1, "John Updated", 26, "California", "Full-time", 60000);
        String result = employeeService.updateEmployee(1, updated);
        assertEquals("Update Successfully", result);
        assertEquals("John Updated", employee.getName());
        verify(employeeRepo, times(1)).findById(1);
        verify(employeeRepo, times(1)).save(employee);
    }

    @Test
    public void testUpdateEmployee_NotFound() {
        when(employeeRepo.findById(1)).thenReturn(Optional.empty());
        Employee updated = new Employee(1, "John Updated", 26, "California", "Full-time", 60000);
        String result = employeeService.updateEmployee(1, updated);
        assertEquals("Employee with ID 1 not found", result);
        verify(employeeRepo, times(1)).findById(1);
        verify(employeeRepo, never()).save(any(Employee.class));
    }

    @Test
    public void testPartialUpdateEmployee_Found() {
        when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
        Employee partialUpdate = new Employee();
        partialUpdate.setName("Johnny");
        partialUpdate.setSalary(55000);
        when(employeeRepo.save(any(Employee.class))).thenReturn(employee);

        String result = employeeService.partialUpdateEmployee(1, partialUpdate);

        assertEquals("Partial update successful", result);
        assertEquals("Johnny", employee.getName());
        assertEquals(55000, employee.getSalary());
        verify(employeeRepo, times(1)).findById(1);
        verify(employeeRepo, times(1)).save(employee);
    }

    @Test
    public void testPartialUpdateEmployee_NotFound() {
        when(employeeRepo.findById(1)).thenReturn(Optional.empty());
        Employee partialUpdate = new Employee();
        partialUpdate.setName("Johnny");

        String result = employeeService.partialUpdateEmployee(1, partialUpdate);

        assertEquals("Employee with ID 1 not found", result);
        verify(employeeRepo, times(1)).findById(1);
        verify(employeeRepo, never()).save(any(Employee.class));
    }
}
