import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="classEmployees")
public class ClassEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "groupName", nullable = false)
    private String groupName;

    @ManyToMany(mappedBy = "classEmployee", cascade = CascadeType.ALL)
    private List<Employee> employeeList = new ArrayList<>();

    @Column(name = "maxEmployeeListSize", nullable = false)
    private int maxEmployeeListSize;

    public ClassEmployee() {}

    public ClassEmployee(String groupName, int maxEmployeeListSize) {
        this.groupName = groupName;
        this.maxEmployeeListSize = maxEmployeeListSize;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public int getMaxEmployeeListSize() {
        return maxEmployeeListSize;
    }

    public void setMaxEmployeeListSize(int maxEmployeeListSize) {
        this.maxEmployeeListSize = maxEmployeeListSize;
    }
}