package com.example.demo.employee;

import com.example.demo.EmployeeCondition;
import com.example.demo.classEmployee.ClassEmployeeRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private ClassEmployeeRepository classEmployeeRepository;

    private EmployeeService underTest;


    @BeforeEach
    void setUp() {
        underTest = new EmployeeService(employeeRepository, classEmployeeRepository);
    }

    @Test
    void canGetEmployees() {
        //when
        underTest.getEmployees();
        //then
        verify(employeeRepository).findAll();
    }

    @Test

    void canCreateEmployee() {
        //given
        Employee employee = new Employee("Piotr", "Tymula", EmployeeCondition.CHORY, 2000, 1000);
        //when
        underTest.createEmployee(employee);
        //then
        ArgumentCaptor<Employee> employeeArgumentCaptor =
                ArgumentCaptor.forClass(Employee.class);

        //capture the employee
        verify(employeeRepository)
                .save(employeeArgumentCaptor.capture());

        Employee capturedEmployee = employeeArgumentCaptor.getValue();
        //compare employees
        assertThat(capturedEmployee).isEqualTo(employee);


    }

    @Test
    @Disabled
    void deleteEmployee() {
    }

    @Test
    @Disabled
    void updateEmployee() {
    }

    @Test
    @Disabled
    void addEmployeeToClassEmployee() {
    }
}