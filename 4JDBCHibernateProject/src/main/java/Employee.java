import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="employees")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="firstName", nullable = false)
    private String firstName;

    @Column(name="lastName", nullable = false)
    private String lastName;

    @Column(name="employeeCondition", nullable = false)
    private String employeeCondition;

    @Column(name="birthYear", nullable = false)
    private int birthYear;

    @Column(name="salary", nullable = false)
    private float salary;

    public Employee() {}

    public Employee(String firstName, String lastName, String employeeCondition, int birthYear, float salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeCondition = employeeCondition;
        this.birthYear = birthYear;
        this.salary = salary;
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeCondition() {
        return employeeCondition;
    }

    public void setEmployeeCondition(String employeeCondition) {
        this.employeeCondition = employeeCondition;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employeeCondition='" + employeeCondition + '\'' +
                ", birthYear=" + birthYear +
                ", salary=" + salary +
                '}';
    }
}