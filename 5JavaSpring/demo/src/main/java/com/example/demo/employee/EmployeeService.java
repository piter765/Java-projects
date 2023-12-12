package com.example.demo.employee;

import com.example.demo.EmployeeCondition;
import com.example.demo.classEmployee.ClassEmployee;
import com.example.demo.classEmployee.ClassEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final ClassEmployeeRepository classEmployeeRepository;


    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ClassEmployeeRepository classEmployeeRepository) {
        this.employeeRepository = employeeRepository;
        this.classEmployeeRepository = classEmployeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void createEmployee(Employee employee) {
        boolean existsEmployee = employeeRepository
                .findEmployeeByFirstNameAndLastName(employee.getFirstName(), employee.getLastName())
                .isPresent();
        if (existsEmployee) {
            throw new IllegalStateException(
                    "Employee with firstName: " + employee.getFirstName()
                            + " and lastName: " + employee.getLastName()
                            +  " already exists"
            );
        }
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if (!exists) {
            throw new IllegalStateException("employee with id " + employeeId + "does not exist");
        }

        employeeRepository.deleteById(employeeId);
    }

    @Transactional
    public void updateEmployee(Integer employeeId, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalStateException("Employee with id " + employeeId + " does not exist"));

        String newFirstName = employeeDTO.getFirstName();
        if (newFirstName != null &&
                newFirstName.length() > 0 &&
                !Objects.equals(employee.getFirstName(), newFirstName)) {
            employee.setFirstName(newFirstName);
        }

        String newLastName = employeeDTO.getLastName();
        if (newLastName != null &&
                newLastName.length() > 0 &&
                !Objects.equals(employee.getLastName(), newLastName)) {
            employee.setLastName(newLastName);
        }

        EmployeeCondition newEmployeeCondition = employeeDTO.getEmployeeCondition();
        if (newEmployeeCondition != null &&
                !Objects.equals(employee.getEmployeeCondition(), newEmployeeCondition)) {
            employee.setEmployeeCondition(newEmployeeCondition);
        }

        Integer newBirthYear = employeeDTO.getBirthYear();
        if (newBirthYear != null &&
                newBirthYear > 0 &&
                !Objects.equals(employee.getBirthYear(), newBirthYear)) {
            employee.setBirthYear(newBirthYear);
        }

        Float newSalary = employeeDTO.getSalary();
        if (newSalary != null &&
                newSalary > 0 &&
                !Objects.equals(employee.getSalary(), newSalary)) {
            employee.setSalary(newSalary);
        }

    }

    @Transactional
    public void addEmployeeToClassEmployee(Integer employeeId, Integer classEmployeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalStateException("Employee with id " + employeeId + " does not exist"));

        ClassEmployee classEmployee = classEmployeeRepository.findById(classEmployeeId)
                .orElseThrow(() -> new IllegalStateException("ClassEmployee with id " + classEmployeeId + " does not exist"));

        if (classEmployeeRepository.findClassEmployeeByEmployeeId(employeeId).isPresent()) {
            throw new IllegalStateException("Employee with id " + employeeId + " already exists in this group.");
        }

        employee.setClassEmployee(classEmployee);

    }
}
