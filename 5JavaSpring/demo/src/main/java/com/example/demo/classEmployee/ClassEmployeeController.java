package com.example.demo.classEmployee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
