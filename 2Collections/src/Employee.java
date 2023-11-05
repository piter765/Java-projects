public class Employee implements Comparable<Employee>{
    private String imie;
    private String nazwisko;
    private EmployeeCondition condition;
    private int rokUrodzenia;
    private double wynagrodzenie;

    Employee(String i, String n, EmployeeCondition c, int r, double w) {
        imie = i;
        nazwisko = n;
        condition = c;
        rokUrodzenia = r;
        wynagrodzenie = w;
    }

    void setSalary(double salary) {
        wynagrodzenie = salary;
    }

    void setCondition(EmployeeCondition c) {
        condition = c;
    }

    String getNazwisko() {
        return this.nazwisko;
    }

    String getImie() {
        return this.imie;
    }

    EmployeeCondition getCondition() {
        return this.condition;
    }

    double getWynagrodzenie() {
        return this.wynagrodzenie;
    }



    void printing() {
        System.out.println("Employee Data:");
        System.out.println("Imie: " + imie);
        System.out.println("Nazwisko: " + nazwisko);
        System.out.println("Condition: " + condition);
        System.out.println("Rok Urodzenia: " + rokUrodzenia);
        System.out.println("Wynagrodzenie: " + wynagrodzenie);
    }

    @Override
    public int compareTo(Employee o) {

         int isEqual1 = this.nazwisko.compareTo(o.nazwisko);
         int isEqual2 = this.imie.compareTo(o.imie);
         return isEqual1 & isEqual2;
    }
}
