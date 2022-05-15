package app.repository;
import app.AbstractRepository;
import app.database.Manager;

import app.entities.ContinentsEntity;

import javax.persistence.TypedQuery;
import java.util.List;

public class ContinentRepo implements AbstractRepository<ContinentsEntity> {

    @Override
    public void save(ContinentsEntity continent) {
        Manager.getInstance().saveObject(continent);
    }

    @Override
    public ContinentsEntity findById(Long id) {
        TypedQuery<ContinentsEntity> query = Manager.getInstance().getEntityManager()
                .createNamedQuery("CONTINENTS.findById", ContinentsEntity.class)
                .setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public List<ContinentsEntity> findByName(String name) {
        TypedQuery<ContinentsEntity> query = Manager.getInstance().getEntityManager()
                .createNamedQuery("CONTINENTS.findByName", ContinentsEntity.class)
                .setParameter("name", name);

        return query.getResultList();
    }
}
