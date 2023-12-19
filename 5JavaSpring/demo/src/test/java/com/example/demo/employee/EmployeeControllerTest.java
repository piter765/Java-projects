package com.example.demo.employee;

import com.example.demo.EmployeeCondition;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void shouldGetEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());

        when(employeeService.getEmployees()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(employees)))
                .andDo(print());

        verify(employeeService).getEmployees();

    }

    @Test
    void shouldGetEmployeesInCSV() throws Exception {

        when(employeeService.getEmployeesInCsv()).thenReturn(createMockCsvResponse());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/csv")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", "text/csv"))
                .andExpect(MockMvcResultMatchers.header().string("Content-Disposition", "attachment; filename=employees.csv"))
                .andExpect(MockMvcResultMatchers.content().string(createExpectedCsvContent()));
    }

    private ResponseEntity<byte[]> createMockCsvResponse() {
        byte[] mockCsvData = "firstName,lastName,birthYear,salary,employeeCondition\nJohn,Doe,1990,5000,DELEGACJA\n".getBytes();
        return ResponseEntity.ok()
                .header("Content-Type", "text/csv")
                .header("Content-Disposition", "attachment; filename=employees.csv")
                .body(mockCsvData);
    }

    private String createExpectedCsvContent() {
        return "firstName,lastName,birthYear,salary,employeeCondition\nJohn,Doe,1990,5000,DELEGACJA\n";
    }

    @Test
    void shouldCreateEmployee() throws Exception {
        Employee employee = new Employee("John", "Doe", EmployeeCondition.CHORY, 1990, 50000);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(print());

        ArgumentCaptor<Employee> employeeCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeService).createEmployee(employeeCaptor.capture());

        Employee capturedEmployee = employeeCaptor.getValue();

        assertEquals(capturedEmployee.getLastName(), employee.getLastName());
        assertEquals(capturedEmployee.getFirstName(), employee.getFirstName());
    }

    @Test
    void shouldDeleteEmployee() throws Exception {
        Integer employeeId = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employee/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(print());

        verify(employeeService).deleteEmployee(employeeId);
    }
    @Test
    @Disabled
    void shouldUpdateEmployee() {
    }

    @Test
    @Disabled
    void addEmployeeToClassEmployee() {
    }
}