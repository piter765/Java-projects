package com.example.demo.classEmployee;

import com.example.demo.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path="/api/group")
public class ClassEmployeeController {

    private final ClassEmployeeService classEmployeeService;

    @Autowired
    public ClassEmployeeController(ClassEmployeeService employeeService) {
        this.classEmployeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<?> getClassEmployees() {
        try {
            List<ClassEmployee> classEmployees = classEmployeeService.getClassEmployees();
            return ResponseEntity.ok(classEmployees);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(path = "{groupId}/employee")
    public ResponseEntity<?> getEmployeesFromClassEmployee(@PathVariable("groupId") Integer groupId) {
        try {
            Set<Employee> employees = classEmployeeService.getEmployeesFromClassEmployee(groupId);
            return ResponseEntity.ok(employees);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(path = "{groupId}/fill")
    public ResponseEntity<?> getClassEmployeeFill(@PathVariable Integer groupId) {
        try {
            double fill = classEmployeeService.getClassEmployeeFill(groupId);
            return ResponseEntity.ok(fill);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createClassEmployee(@RequestBody ClassEmployee classEmployee) {
        try {
            classEmployeeService.createClassEmployee(classEmployee);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "{classEmployeeId}")
    public ResponseEntity<?> deleteClassEmployee(@PathVariable("classEmployeeId") Integer id) {
        try {
            classEmployeeService.deleteClassEmployee(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PatchMapping(path = "{classEmployeeId}")
    public ResponseEntity<Void> updateClassEmployee(
            @PathVariable("classEmployeeId") Integer classEmployeeId,
            @RequestBody(required = false) ClassEmployee classEmployee) {
        try {
            classEmployeeService.updateClassEmployee(classEmployeeId, classEmployee);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(404).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}
