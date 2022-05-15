package app.database;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Manager {
    private static Manager manager = null;
    private final EntityManager entityManager;
    private Manager() {
        this.entityManager = Persistence.createEntityManagerFactory("persistenceUnit").createEntityManager();
    }
    public static Manager getInstance() {
        if (manager == null) {
            manager = new Manager();
        }
        return manager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
    public void close() {
        entityManager.close();
    }
    public void saveObject(Object object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }
}
