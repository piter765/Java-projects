package com.example.demo.employee;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping
    public void createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
    }

    @DeleteMapping (path="{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") Integer id) {
        employeeService.deleteEmployee(id);
    }

    @PatchMapping (path="{employeeId}")
    public void updateEmployee(@PathVariable("employeeId") Integer employeeId,
                               @RequestBody(required = true) EmployeeDTO request) {
        employeeService.updateEmployee(employeeId, request);

    }
}
