package com.example.demo.classEmployee;

import com.example.demo.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClassEmployeeRepository extends JpaRepository<ClassEmployee, Integer> {

    @Query("SELECT c FROM ClassEmployee c INNER JOIN Employee e ON c.id = e.classEmployee.id WHERE e.id = :employeeId")
    Optional<ClassEmployee> findClassEmployeeByEmployeeId(@Param("employeeId") Integer employeeId);

    @Query("SELECT e FROM Employee e WHERE e.classEmployee.id = :classEmployeeId")
    List<Employee> findEmployeesByClassEmployeeId(@Param("classEmployeeId") Integer classEmployeeId);

}

