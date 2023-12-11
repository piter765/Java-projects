package com.example.demo.classEmployee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ClassEmployeeConfig {

    @Bean
    CommandLineRunner classEmployeeCommandLineRunner(ClassEmployeeRepository classEmployeeRepository) {
        return args -> {
            ClassEmployee e1 = new ClassEmployee("Group1", 10);

            ClassEmployee e2 = new ClassEmployee("Group2", 15);

            classEmployeeRepository.saveAll(List.of(e1, e2));
        };

    }
}
