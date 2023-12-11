package com.example.demo.classEmployee;

import com.example.demo.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class ClassEmployeeService {

    private final ClassEmployeeRepository classEmployeeRepository;

    @Autowired
    public ClassEmployeeService(ClassEmployeeRepository classEmployeeRepository) {
        this.classEmployeeRepository = classEmployeeRepository;
    }

    public List<ClassEmployee> getClassEmployees() {
        return classEmployeeRepository.findAll();
    }

    public void createClassEmployee(ClassEmployee classEmployee) {
        classEmployeeRepository.save(classEmployee);
    }

    public void deleteClassEmployee(Integer classEmployeeId) {
        boolean exists = classEmployeeRepository.existsById(classEmployeeId);

        if (!exists) {
           throw new IllegalStateException("group with id " + classEmployeeId + "does not exist");
        }

        classEmployeeRepository.deleteById(classEmployeeId);
    }

    @Transactional
    public void updateClassEmployee(Integer classEmployeeId, ClassEmployee classEmployeeUpdate) {

        ClassEmployee classEmployee = classEmployeeRepository.findById(classEmployeeId)
                .orElseThrow(() -> new IllegalStateException("Group with id " + classEmployeeId + " does not exist"));

        String groupName = classEmployeeUpdate.getGroupName();
        Integer maxEmployeeListSize = classEmployeeUpdate.getMaxEmployeeListSize();

        if (groupName != null &&
                groupName.length() > 0 &&
                !Objects.equals(classEmployee.getGroupName(), groupName)) {
            classEmployee.setGroupName(groupName);
        }

        if (maxEmployeeListSize != null &&
                maxEmployeeListSize > 0 &&
                !Objects.equals(classEmployee.getMaxEmployeeListSize(), maxEmployeeListSize)) {
            classEmployee.setMaxEmployeeListSize(maxEmployeeListSize);
        }
    }

    public Set<Employee> getEmployeesFromClassEmployee(Integer classEmployeeId) {
        ClassEmployee classEmployee = classEmployeeRepository.findById(classEmployeeId)
                .orElseThrow(() -> new IllegalStateException("Group with id " + classEmployeeId + " does not exist"));

        return classEmployee.getEmployees();
    }
}
