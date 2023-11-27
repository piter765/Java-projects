import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import models.Employee;

public class HibernateUtil {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("myPersistenceUnit");

    public static void main(String[] args) {

        ENTITY_MANAGER_FACTORY.close();

    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }
}