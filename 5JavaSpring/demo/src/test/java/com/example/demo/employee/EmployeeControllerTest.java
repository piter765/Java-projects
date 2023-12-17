package com.example.demo.employee;

import com.example.demo.EmployeeCondition;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    @Disabled
    void getEmployeesInCsv() throws Exception {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());

        when(employeeService.getEmployees()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/csv")
                        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/csv"))
                .andExpect(MockMvcResultMatchers.header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees.csv")) // Expect the content disposition header
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(employees)))
                .andDo(print());

        verify(employeeService).getEmployeesInCsv();
    }

    @Test
    void shouldCreateEmployee() throws Exception {
        Employee employee = new Employee("John", "Doe", EmployeeCondition.CHORY, 1990, 50000);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());

        verify(employeeService).createEmployee(employee);
    }

    @Test
    void shouldDeleteEmployee() throws Exception {
        Integer employeeId = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employee/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
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