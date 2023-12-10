package com.example.demo.employee;

import com.example.demo.EmployeeCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/employees")
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
                               @RequestBody(required = true) EmployeeUpdateRequest request) {
        employeeService.updateEmployee(employeeId, request);

    }
}
