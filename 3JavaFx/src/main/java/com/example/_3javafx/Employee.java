package com.example._3javafx;

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

    public void setSalary(double salary) {
        wynagrodzenie = salary;
    }

    public void setCondition(EmployeeCondition c) {
        condition = c;
    }

    public String getNazwisko() {
        return this.nazwisko;
    }

    public String getImie() {
        return this.imie;
    }

    public EmployeeCondition getCondition() {
        return this.condition;
    }

    public double getWynagrodzenie() {
        return this.wynagrodzenie;
    }

    public int getRokUrodzenia() {return this.rokUrodzenia;}

    public void setImie(String x) {imie = x;}
    public void setNazwisko(String x) {nazwisko = x;}
    public void setRokUrodzenia(int x) {rokUrodzenia = x;}
    public void setWynagrodzenie(double x) {wynagrodzenie = x;}


    @Override
    public String toString() {
        return "{name='" + imie + "', lastName='" + nazwisko + "', condition='" + condition + "', salary=" + wynagrodzenie + "}";
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
        int lastNameComparison = this.nazwisko.compareTo(o.nazwisko);
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }

        // If last names are the same, compare by first names
        return this.imie.compareTo(o.imie);
    }
}
