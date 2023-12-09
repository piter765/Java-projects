package com.example.demo.employee;

import com.example.demo.EmployeeCondition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository) {
        return args -> {
            Employee e1 = new Employee("Piotr", "Tymula", EmployeeCondition.CHORY, 2000, 1000);

            Employee e2 = new Employee("Michal", "Min", EmployeeCondition.DELEGACJA, 1999, 10000);

            repository.saveAll(List.of(e1, e2));
        };

    }
}
