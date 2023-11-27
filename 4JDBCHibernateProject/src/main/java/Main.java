import models.Employee;

public class Main {

    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController();
        ClassEmployeeController classEmployeeController = new ClassEmployeeController();

        Employee employee = new Employee("Piotr", "Tymula", "CHORY", 12, 1000);
        employeeController.createEmployee(employee);
        employeeController.getEmployee(1);
        System.out.println("All employees");
        employeeController.getEmployees();
        employeeController.deleteEmployee(1);
        employeeController.updateEmployee(3, "X", "Y");
        employeeController.getEmployees();

        //ClassEmployee classEmployee = new ClassEmployee("class1", 100);
        //createClass(classEmployee);

        //ClassEmployeeController.addEmployeeToClass(3, "class1");
        ClassEmployeeController.removeEmployeeFromClass(3);

        EmployeeController.closeEntityManagerFactory();
        ClassEmployeeController.closeEntityManagerFactory();
    }
}
