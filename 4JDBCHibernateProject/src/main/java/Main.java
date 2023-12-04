import models.ClassEmployee;
import models.Employee;
import models.EmployeeCondition;
import models.Rate;

public class Main {

    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController();
        ClassEmployeeController classEmployeeController = new ClassEmployeeController();

        Employee employee = new Employee("Piotr", "Tymula", EmployeeCondition.CHORY, 12, 1000);
        employeeController.createEmployee(employee);
        System.out.println("Employee found by id: ");
        employeeController.getEmployee(1);
        System.out.println("All employees");
        employeeController.getEmployees();

        employeeController.deleteEmployee(21);
        System.out.println("Updated employee:");
        employeeController.updateEmployee(20, "X", "Y");
        employeeController.getEmployee(20);
        employeeController.search("Tym");

        ClassEmployee classEmployee = new ClassEmployee("class3", 100);
        ClassEmployeeController.createClass(classEmployee);

        ClassEmployeeController.addEmployeeToClass(19, "class3");
        ClassEmployeeController.addEmployeeToClass(18, "class1");
        classEmployee.calculateOccupancyRate();
        ClassEmployeeController.removeEmployeeFromClass(19);

        Rate rate = new Rate(2, classEmployee, "Pretty bad." );

        RateController.createRate(rate);
        //RateController.deleteRate(3);

        EmployeeController.closeEntityManagerFactory();
        ClassEmployeeController.closeEntityManagerFactory();
        RateController.closeEntityManagerFactory();
    }
}
