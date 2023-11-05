import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Employee employee4 = new Employee("Test4", "Test4", EmployeeCondition.DELEGACJA, 1999, 4000.0);
        Employee employee1 = new Employee("Test1", "Test1", EmployeeCondition.CHORY, 2002, 10000.0);
        Employee employee2 = new Employee("Test2", "Test2", EmployeeCondition.OBECNY, 2001, 9000.0);
        Employee employee3 = new Employee("Test3", "Test3", EmployeeCondition.NIEOBECNY, 2000, 5000.0);

        employee1.printing();
        System.out.println(employee1.compareTo(employee2));

        ClassEmployee grupa = new ClassEmployee("grupa1", 10);
        grupa.addEmployee(employee1);
        grupa.addEmployee(employee2);
        grupa.addEmployee(employee3);
        grupa.addEmployee(employee4);

        grupa.addSalary(employee2, 50);
        grupa.printing();

        grupa.removeEmployee(employee2);
        grupa.printing();

        grupa.changeCondition(employee1, EmployeeCondition.DELEGACJA);
        grupa.printing();

        Employee e = grupa.search("Test3");
        System.out.println("SEARCH");
        e.printing();

        //SEARCH PARTIAL
        System.out.println("\nSEARCH PARTIAL");
        List<Employee> lista = grupa.searchPartial("3");
        for (Employee x : lista) {
            x.printing();
        }

        //COUNT BY CONDITION
        System.out.println("\nCOUNT BY CONDITION\n");
        grupa.printing();
        System.out.println(grupa.countByCondition(EmployeeCondition.DELEGACJA));

        //SUMMARY
        System.out.println("\nSUMMARY\n");
        grupa.summary();

        //SORT BY NAME
        System.out.println("\nSORT BY NAME\n");
        List<Employee> lista2 = grupa.sortByName();

        for (Employee x : lista2) {
            x.printing();
        }

        //SORT BY SALARY
        System.out.println("\nSORT BY SALARY\n");
        List<Employee> lista3 = grupa.sortBySalary();

        for (Employee x : lista3) {
            x.printing();
        }

        //MAX
        System.out.println("\nMAX\n");
        System.out.println(grupa.max());

        //CLASS CONTAINER
        System.out.println("\nKLASA\n");
        ClassContainer klasa = new ClassContainer();
        klasa.addClass("grupaZKlasy", 25);

        klasa.summary();

        //FIND EMPTY
        System.out.println("\nFIND EMPTY\n");
        List<String> pusteGrupy = klasa.findEmpty();
        for (String name: pusteGrupy) {
            System.out.println(name);
        }

        //REMOVE CLASS
        System.out.println("\nREMOVE CLASS\n");
        klasa.removeClass("grupaZKlasy");
        klasa.summary();

    }
}
