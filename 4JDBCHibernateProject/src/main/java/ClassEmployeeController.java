import models.ClassEmployee;
import models.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ClassEmployeeController {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = HibernateUtil.getEntityManagerFactory();

    public static void closeEntityManagerFactory() {
        ENTITY_MANAGER_FACTORY.close();
    }

    //CLASS EMPLOYEE
    public static void createClass(ClassEmployee classEmployee) {
        EntityTransaction transaction = null;
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(classEmployee);

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

    public static void getClass(int id) {
        EntityTransaction transaction = null;
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

        String query = "SELECT c FROM ClassEmployee c WHERE c.id = :classEmployeeId";

        TypedQuery<ClassEmployee> emp = entityManager.createQuery(query, ClassEmployee.class);
        emp.setParameter("classEmployeeId", id);
        ClassEmployee classEmployee = null;
        try {
            classEmployee = emp.getSingleResult();
            System.out.println(classEmployee.getGroupName());

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

    public static void getClasses() {
        EntityTransaction transaction = null;
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

        String query = "SELECT c FROM ClassEmployee c";
        TypedQuery<ClassEmployee> emp = entityManager.createQuery(query, ClassEmployee.class);

        List<ClassEmployee> classes = new ArrayList<ClassEmployee>();

        try {
            classes  = emp.getResultList();
            for(ClassEmployee e : classes) {
                System.out.println(e.getId() + " " + e.getGroupName());
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

    public static void updateClassEmployee(int id, String groupName, int maxEmployeeListSize) {
        EntityTransaction transaction = null;
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        ClassEmployee classEmployee = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            classEmployee = entityManager.find(ClassEmployee.class, id);
            classEmployee.setGroupName(groupName);
            classEmployee.setMaxEmployeeListSize(maxEmployeeListSize);

            entityManager.persist(classEmployee);

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

    public static void deleteClassEmployee(int id) {
        EntityTransaction transaction = null;
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        ClassEmployee classEmployee = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            classEmployee = entityManager.find(ClassEmployee.class, id);

            entityManager.remove(classEmployee);

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

    public static void addEmployeeToClass(int employeeId, String className) {
        EntityTransaction transaction = null;
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        ClassEmployee classEmployee = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Find the ClassEmployee by class name
            String queryStr = "SELECT c FROM ClassEmployee c WHERE groupName = :className";
            TypedQuery<ClassEmployee> query = entityManager.createQuery(queryStr, ClassEmployee.class);
            query.setParameter("className", className);
            classEmployee = query.getSingleResult();

            // Check if the classEmployee exists and the limit is not exceeded
            if (classEmployee != null && classEmployee.getEmployees().size() < classEmployee.getMaxEmployeeListSize()) {
                // Find the Employee by employeeId
                Employee employee = entityManager.find(Employee.class, employeeId);

                // Check if the employee exists
                if (employee != null) {

                    classEmployee.getEmployees().add(employee);

                    employee.setClass(classEmployee);

                    entityManager.persist(classEmployee);
                    entityManager.persist(employee);
                } else {
                    System.out.println("Employee not found with ID: " + employeeId);
                }
            } else {
                System.out.println("Class not found or limit exceeded for class: " + className);
            }

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

    public static void removeEmployeeFromClass(int employeeId) {
        EntityTransaction transaction = null;
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        ClassEmployee classEmployee = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();


            Employee employee = entityManager.find(Employee.class, employeeId);

            if (employee != null) {

                employee.setClass(null);

                // Update the Employee entity
                entityManager.persist(employee);

                System.out.println("Employee removed from class successfully.");
            } else {
                System.out.println("Employee not found with ID: " + employeeId);
            }

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
