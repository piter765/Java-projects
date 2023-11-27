import models.ClassEmployee;
import models.Employee;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = HibernateUtil.getEntityManagerFactory();

    public static void closeEntityManagerFactory() {
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void createEmployee(Employee employee) {
        EntityTransaction transaction = null;
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(employee);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public static void getEmployee(int id) {
        EntityTransaction transaction = null;
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

            String query = "SELECT e FROM Employee e WHERE e.id = :employeeId";

            TypedQuery<Employee> emp = entityManager.createQuery(query, Employee.class);
            emp.setParameter("employeeId", id);
            Employee employee = null;
        try {
            employee = emp.getSingleResult();
            System.out.println(employee.getFirstName());

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public static void getEmployees() {
        EntityTransaction transaction = null;
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

        String query = "SELECT e FROM Employee e";
        TypedQuery<Employee> emp = entityManager.createQuery(query, Employee.class);

        List<Employee> employees = new ArrayList<Employee>();

        try {
            employees = emp.getResultList();
            for(Employee e : employees) {
                System.out.println(e.getId() + " " + e.getFirstName());
            }

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public static void updateEmployee(int id, String firstName, String lastName) {
        EntityTransaction transaction = null;
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        Employee employee = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            employee = entityManager.find(Employee.class, id);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);

            entityManager.persist(employee);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public static void deleteEmployee(int id) {
        EntityTransaction transaction = null;
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        Employee employee = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            employee = entityManager.find(Employee.class, id);

            entityManager.remove(employee);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

}