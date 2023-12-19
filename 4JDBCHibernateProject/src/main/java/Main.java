import models.ClassEmployee;
import models.Employee;
import models.EmployeeCondition;
import models.Rate;

public class Main {

    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController();
        ClassEmployeeController classEmployeeController = new ClassEmployeeController();

        Employee employee = new Employee("Piotr", "Tymula", EmployeeCondition.CHORY, 15, 1000);
        employeeController.createEmployee(employee);
        System.out.println("Employee found by id: ");
        employeeController.getEmployee(1);
        System.out.println("All employees");
        employeeController.getEmployees();

        //employeeController.deleteEmployee(21);
        System.out.println("Updated employee:");
        employeeController.updateEmployee(31, "X", "Y");
        employeeController.getEmployee(20);
        employeeController.search("Tym");

        ClassEmployee classEmployee = new ClassEmployee("class5", 100);
        ClassEmployeeController.createClass(classEmployee);

        Rate rate = new Rate(2, classEmployee, "Pretty bad." );

        RateController.createRate(rate);

        //ClassEmployeeController.addEmployeeToClass(31, "class1");
        //ClassEmployeeController.addEmployeeToClass(18, "class1");
        System.out.println(classEmployee.calculateOccupancyRate());
        ClassEmployeeController.removeEmployeeFromClass(31);

        //Rate rate = new Rate(2, classEmployee, "Pretty bad." );

        RateController.createRate(rate);
        //RateController.deleteRate(3);

        EmployeeController.closeEntityManagerFactory();
        ClassEmployeeController.closeEntityManagerFactory();
        RateController.closeEntityManagerFactory();
    }
}
