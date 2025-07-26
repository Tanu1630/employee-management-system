package SpringBoot_API.SpringBoot_Rest_API.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import SpringBoot_API.SpringBoot_Rest_API.model.Employee;
import SpringBoot_API.SpringBoot_Rest_API.serviceIMPL.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeServiceImpl employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/emp/home"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to Employee Management"));
    }

    @Test
    void testAddEmployee() throws Exception {
        Employee emp = new Employee(1, "John", 25, "Texas", "Developer", 50000);

        when(employeeService.AddEmployee(any(Employee.class))).thenReturn(emp);

        mockMvc.perform(MockMvcRequestBuilders.post("/emp/addEmp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void testRemoveEmployee() throws Exception {
        when(employeeService.removeEmployee(1)).thenReturn("Remove Successfully");

        mockMvc.perform(MockMvcRequestBuilders.delete("/emp/removeEmp/1"))
                .andExpect(status().isAccepted())
                .andExpect(content().string("Remove Successfully"));

        verify(employeeService, times(1)).removeEmployee(1);
    }

   @Test
void testFindEmployeeById() throws Exception {
    Employee emp = new Employee(1, "John", 25, "Texas", "Developer", 50000);
    when(employeeService.findEmployeeById(1)).thenReturn(Optional.of(emp));

    mockMvc.perform(MockMvcRequestBuilders.get("/emp/findEmp/1"))
            .andExpect(status().isAccepted())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("John"))
            .andExpect(jsonPath("$.state").value("Texas"))
            .andExpect(jsonPath("$.type").value("Developer"))
            .andExpect(jsonPath("$.salary").value(50000));
}

    @Test
    void testListOfEmployees() throws Exception {
        Employee emp1 = new Employee(1, "John", 25, "Texas", "Developer", 50000);
        Employee emp2 = new Employee(2, "Alice", 30, "California", "Manager", 70000);

        when(employeeService.getAllEmployee()).thenReturn(Arrays.asList(emp1, emp2));

        mockMvc.perform(MockMvcRequestBuilders.get("/emp/all"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[1].name").value("Alice"));
    }

    @Test
    void testUpdateEmployee_Success() throws Exception {
        Employee updatedEmp = new Employee(1, "John Updated", 26, "Florida", "Lead", 60000);

        when(employeeService.updateEmployee(eq(1), any(Employee.class))).thenReturn("Update Successfully");

        mockMvc.perform(MockMvcRequestBuilders.put("/emp/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmp)))
                .andExpect(status().isOk())
                .andExpect(content().string("Update Successfully"));
    }

    @Test
    void testUpdateEmployee_NotFound() throws Exception {
        Employee updatedEmp = new Employee(1, "John Updated", 26, "Florida", "Lead", 60000);

        when(employeeService.updateEmployee(eq(99), any(Employee.class))).thenReturn("Employee not found");

        mockMvc.perform(MockMvcRequestBuilders.put("/emp/update/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmp)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Employee not found"));
    }
}
