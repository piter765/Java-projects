package com.example.demo.classEmployee;

import com.example.demo.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ClassEmployee> getClassEmployees() {
        return classEmployeeService.getClassEmployees();
    }

    @GetMapping(path="{groupId}/employee")
    public Set<Employee> getEmployeesFromClassEmployee(@PathVariable("groupId") Integer groupId) {
        return classEmployeeService.getEmployeesFromClassEmployee(groupId);
    }

    @GetMapping(path="{groupId}/fill")
    public double getClassEmployeeFill(@PathVariable Integer groupId) {
        return classEmployeeService.getClassEmployeeFill(groupId);
    }

    @PostMapping
    public void createClassEmployee(@RequestBody ClassEmployee classEmployee) {
        classEmployeeService.createClassEmployee(classEmployee);
    }

    @DeleteMapping(path="{classEmployeeId}")
    public void deleteEmployee(@PathVariable("classEmployeeId") Integer id) {
        classEmployeeService.deleteClassEmployee(id);
    }

    @PatchMapping (path="{classEmployeeId}")
    public void updateClassEmployee(@PathVariable("classEmployeeId") Integer classEmployeeId,
                               @RequestBody(required = false) ClassEmployee classEmployee
                                    ) {
        classEmployeeService.updateClassEmployee(classEmployeeId, classEmployee);
    }

}
