import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassContainer {
    private Map<String, ClassEmployee> employeeGroups;

    public ClassContainer() {
        employeeGroups = new HashMap<>();
    }

    public void addClass(String groupName, int n) {
        ClassEmployee grupa = new ClassEmployee(groupName, n);
        Employee employee4 = new Employee("Test4", "Test4", EmployeeCondition.DELEGACJA, 1999, 4000.0);
        grupa.addEmployee(employee4);
        employeeGroups.put(groupName, grupa);
    }

    public ClassEmployee getEmployeeGroup(String nazwaGrupy) {
        return employeeGroups.get(nazwaGrupy);
    }

    public void removeClass(String nazwaGrupy) {
        employeeGroups.remove(nazwaGrupy);
    }

    public List<String> findEmpty() {
        List<String> emptyGroups = new ArrayList<>();
        for (Map.Entry<String, ClassEmployee> entry : employeeGroups.entrySet()) {
            String groupName = entry.getKey();
            ClassEmployee group = entry.getValue();

            if (group.getEmployeeList().isEmpty()) {
                emptyGroups.add(groupName);
            }
        }
        return emptyGroups;
    }

    public void summary() {
        for (Map.Entry<String, ClassEmployee> entry : employeeGroups.entrySet()) {
            String className = entry.getKey();
            ClassEmployee employeeGroup = entry.getValue();

            System.out.println(employeeGroup.getEmployeeListSize());
            System.out.println(employeeGroup.getMaxEmployeeListSize());

            double occupancy = (double) employeeGroup.getEmployeeListSize() / (double) employeeGroup.getMaxEmployeeListSize() * 100;
            System.out.println("Grupa: " + className + ", Zapełnienie: " + occupancy + "%");
        }
    }
}