package com.example.demo.employee;

import java.util.List;

public class EmployeeHelpers {

    public static String generateCsvContentForEmployees(List<Employee> employees) {
        StringBuilder csvContent = new StringBuilder();
        csvContent.append("ID,firstName,lastName,birthYear,EmployeeCondition,salary\n");

        for (Employee employee : employees) {
            csvContent.append(employee.getId())
                    .append(",")
                    .append(employee.getFirstName())
                    .append(",")
                    .append(employee.getLastName())
                    .append(",")
                    .append(employee.getBirthYear())
                    .append(",")
                    .append(employee.getEmployeeCondition())
                    .append(",")
                    .append(employee.getSalary())
                    .append("\n");
        }

        return csvContent.toString();
    }
}
