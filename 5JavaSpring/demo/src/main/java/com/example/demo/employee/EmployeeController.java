package com.example.demo.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<?> getEmployees() {
        try {
            List<Employee> employees = employeeService.getEmployees();
            return ResponseEntity.status(HttpStatus.OK).body(employees);
        } catch (Exception e) {
            String errorMessage = "Failed to retrieve employees: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping(path = "/csv")
    public ResponseEntity<?> getEmployeesInCsv() {
        try {
            return employeeService.getEmployeesInCsv();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Something went wrong with fetching the employees");
        }
    }

    @PostMapping
    public ResponseEntity<Void> createEmployee(@RequestBody Employee employee) {
        try {
            employeeService.createEmployee(employee);
            return ResponseEntity.status(201).build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeId") Integer id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(404).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<Void> updateEmployee(
            @PathVariable("employeeId") Integer employeeId,
            @RequestBody EmployeeDTO request) {
        try {
            employeeService.updateEmployee(employeeId, request);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(404).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping(path = "/{employeeId}/group/{groupId}")
    public ResponseEntity<String> addEmployeeToClassEmployee(
            @PathVariable("employeeId") Integer employeeId,
            @PathVariable("groupId") Integer classEmployeeId) {
        try {
            employeeService.addEmployeeToClassEmployee(employeeId, classEmployeeId);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
