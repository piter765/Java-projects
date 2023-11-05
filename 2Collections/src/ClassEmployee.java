import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClassEmployee implements Comparator<String> {
    private String nazwaGrupy;
    private List<Employee> listaPracownikow;
    private int maksymalnaIloscPracownikow;

    public ClassEmployee(String nazwaGrupy, int maksymalnaIloscPracownikow) {
        this.nazwaGrupy = nazwaGrupy;
        this.maksymalnaIloscPracownikow = maksymalnaIloscPracownikow;
        this.listaPracownikow = new ArrayList<>();
    }

    public String getGroupName() {
        return nazwaGrupy;
    }

    // Metoda do dodawania pracownika do listy
    public void addEmployee(Employee pracownik) {
        if (listaPracownikow.size() < maksymalnaIloscPracownikow) {

            for (Employee existingEmployee : listaPracownikow) {
                if (existingEmployee.compareTo(pracownik) == 0) {
                    System.out.println("Pracownik o tym imieniu i nazwisku już istnieje.");
                    return;
                }
            }

            listaPracownikow.add(pracownik);
        } else {
            System.out.println("Grupa pracownicza jest już pełna. Nie można dodać więcej pracowników.");
        }
    }

    public void addSalary(Employee pracownik, double wynagrodzenie) {
        for (Employee existingEmployee : listaPracownikow) {
            if (existingEmployee.compareTo(pracownik) == 0) {
                existingEmployee.setSalary(existingEmployee.getWynagrodzenie() + wynagrodzenie);
            }
        }
    }

    public void removeEmployee(Employee pracownik) {
        listaPracownikow.remove(pracownik);
    }

    public void changeCondition(Employee pracownik, EmployeeCondition condition) {
        for (Employee existingEmployee : listaPracownikow) {
            if (existingEmployee.compareTo(pracownik) == 0) {
                existingEmployee.setCondition(condition);
            }
        }
    }

    @Override
    public int compare(String n1, String n2) {
        return n1.compareTo(n2);
    }

    public Employee search(String nazwisko) {
        for (Employee existingEmployee : listaPracownikow) {
            if (compare(existingEmployee.getNazwisko(), nazwisko) == 0) {
                return existingEmployee;
            }
        }
        return null;
    }

    public List<Employee> searchPartial(String pattern) {
        List<Employee> list = new ArrayList<Employee>();

        for (Employee existingEmployee : listaPracownikow) {
            if (existingEmployee.getNazwisko().contains(pattern) || existingEmployee.getImie().contains(pattern)) {
                list.add(existingEmployee);
            }
        }
        return list;
    }


    public int countByCondition(EmployeeCondition c) {
        int count = 0;
        for (Employee existingEmployee : listaPracownikow) {
           if (existingEmployee.getCondition() == c) {
               count++;
           }
        }
        return count;
    }

    public void summary() {
        for (Employee existingEmployee : listaPracownikow) {
            existingEmployee.printing();
            System.out.println();
        }
    }

    public List<Employee> sortByName() {

        Comparator<Employee> nameComparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return e1.getImie().compareTo(e2.getImie());
            }
        };

        Collections.sort(listaPracownikow, nameComparator);

        return listaPracownikow;
    }

    public List<Employee> sortBySalary() {

        Comparator<Employee> salaryDescendingComparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return Double.compare(e2.getWynagrodzenie(), e1.getWynagrodzenie());
            }
        };

        // Sort the list of employees by salary in descending order
        Collections.sort(listaPracownikow, salaryDescendingComparator);

        return listaPracownikow;
    }

    public double max() {

        Employee e = Collections.max(listaPracownikow, new Comparator< Employee >() {
            @Override
            public int compare(Employee w1, Employee w2) {
                return Double.compare(w1.getWynagrodzenie(), w2.getWynagrodzenie());
            }
        });

        return e.getWynagrodzenie();
    }


    public void printing() {
        System.out.println("Nazwa Grupy: " + nazwaGrupy);
        System.out.println("Maksymalna Ilość Pracowników: " + maksymalnaIloscPracownikow);
        System.out.println("Liczba Pracowników w Grupie: " + listaPracownikow.size());
        System.out.println("Lista Pracowników:\n");

        for (Employee x : listaPracownikow) {
            x.printing();
            System.out.println();
        }

    }

    public int getEmployeeListSize() {
        return listaPracownikow.size();
    }

    public List<Employee> getEmployeeList() {
        return listaPracownikow;
    }

    public int getMaxEmployeeListSize() {
        return maksymalnaIloscPracownikow;
    }

}
