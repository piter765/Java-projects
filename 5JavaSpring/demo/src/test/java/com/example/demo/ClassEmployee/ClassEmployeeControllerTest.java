package com.example.demo.ClassEmployee;

import com.example.demo.EmployeeCondition;
import com.example.demo.classEmployee.ClassEmployee;
import com.example.demo.classEmployee.ClassEmployeeController;
import com.example.demo.classEmployee.ClassEmployeeService;
import com.example.demo.employee.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClassEmployeeController.class)
class ClassEmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClassEmployeeService classEmployeeService;

    @Test
    void shouldGetClassEmployees() throws Exception {
        List<ClassEmployee> classEmployees = Arrays.asList(new ClassEmployee(), new ClassEmployee());

        when(classEmployeeService.getClassEmployees()).thenReturn(classEmployees);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/group"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(classEmployees)))
                .andDo(print());

        verify(classEmployeeService).getClassEmployees();
    }

    @Test
    void shouldGetEmployeesFromClassEmployee() throws Exception {
        Integer groupId = 1;
        Set<Employee> employees = Set.of(new Employee("Piotr", "Tymula", EmployeeCondition.CHORY, 2000, 1000),
                new Employee("Michal", "Min", EmployeeCondition.DELEGACJA, 1999, 10000));

        when(classEmployeeService.getEmployeesFromClassEmployee(groupId)).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/group/{groupId}/employee", groupId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(employees)))
                .andDo(print());

        verify(classEmployeeService).getEmployeesFromClassEmployee(groupId);
    }

    @Test
    void shouldGetClassEmployeeFill() throws Exception {
        Integer groupId = 1;
        double fill = 50.0;

        when(classEmployeeService.getClassEmployeeFill(groupId)).thenReturn(fill);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/group/{groupId}/fill", groupId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json("50.0"));


        verify(classEmployeeService).getClassEmployeeFill(groupId);

    }

    @Test
    void shouldCreateClassEmployee() throws Exception{
        ClassEmployee classEmployee = new ClassEmployee("Group1", 5);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/group")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(classEmployee)))
                .andExpect(status().isCreated());

        ArgumentCaptor<ClassEmployee> classEmployeeArgumentCaptor = ArgumentCaptor.forClass(ClassEmployee.class);

        verify(classEmployeeService).createClassEmployee(classEmployeeArgumentCaptor.capture());
    }

    @Test
    void shouldDeleteClassEmployee() throws Exception {
        Integer groupId = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/group/{groupId}", groupId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(print());

        verify(classEmployeeService).deleteClassEmployee(groupId);
    }
}