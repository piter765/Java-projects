package com.example.demo.employee;

import com.example.demo.EmployeeCondition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindEmployeeByFirstNameAndLastName() {
        //given
        String firstName = "Piotr";
        String lastName = "Tymula";
        Employee employee = new Employee(firstName, lastName, EmployeeCondition.CHORY, 2000, 1000);

        underTest.save(employee);

        //when
        boolean expected = underTest.findEmployeeByFirstNameAndLastName(firstName, lastName).isPresent();

        //then
        assertThat(expected).isTrue();

    }

    @Test
    void itShouldNotFindEmployeeByFirstNameAndLastName() {
        //given
        String firstName = "Piotr";
        String lastName = "Tymula";

        //when
        boolean expected = underTest.findEmployeeByFirstNameAndLastName(firstName, lastName).isPresent();

        //then
        assertThat(expected).isFalse();
    }
}