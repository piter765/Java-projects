import models.ClassEmployee;
import models.Employee;
import models.Rate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class RateController {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = HibernateUtil.getEntityManagerFactory();

    public static void closeEntityManagerFactory() {
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void createRate(Rate rate) {
        EntityTransaction transaction = null;
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            if (rate.getClassEmployee() != null && rate.getClassEmployee().getId() != 0) {
                rate.setClassEmployee(entityManager.find(ClassEmployee.class, rate.getClassEmployee().getId()));
            } else {
                entityManager.persist(rate.getClassEmployee());
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

    public static void getRate(int id) {
        EntityTransaction transaction = null;
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

        String query = "SELECT r FROM Rate r WHERE r.id = :rateId";

        TypedQuery<Rate> rateQuery = entityManager.createQuery(query, Rate.class);
        rateQuery.setParameter("rateId", id);
        Rate rate = null;
        try {
            rate = rateQuery.getSingleResult();
            System.out.println("Rate value: " + rate.getValue());
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

    public static void getRates() {
        EntityTransaction transaction = null;
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

        String query = "SELECT r FROM Rate r";
        TypedQuery<Rate> rateQuery = entityManager.createQuery(query, Rate.class);

        List<Rate> rates = new ArrayList<>();

        try {
            rates = rateQuery.getResultList();
            for (Rate r : rates) {
                System.out.println("Rate ID: " + r.getId() + ", Value: " + r.getValue());
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

    public static void updateRate(int id, int value, String comment) {
        EntityTransaction transaction = null;
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        Rate rate = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            rate = entityManager.find(Rate.class, id);
            rate.setValue(value);
            rate.setComment(comment);

            entityManager.persist(rate);

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

    public static void deleteRate(int id) {
        EntityTransaction transaction = null;
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        Rate rate = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            rate = entityManager.find(Rate.class, id);

            entityManager.remove(rate);

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
