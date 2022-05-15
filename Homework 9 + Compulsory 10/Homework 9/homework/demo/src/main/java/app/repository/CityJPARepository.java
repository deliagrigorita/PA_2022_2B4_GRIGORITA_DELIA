package app.repository;

import app.entities.CitiesEntity;
import app.database.Manager;
import app.AbstractRepository;

import javax.persistence.TypedQuery;
import java.util.List;

public  class CityJPARepository implements AbstractRepository<CitiesEntity> {

    @Override
    public void save(CitiesEntity city) {
        Manager.getInstance().saveObject(city);
    }

    @Override
    public CitiesEntity findById(Long id) {
        TypedQuery<CitiesEntity> query = Manager.getInstance().getEntityManager()
                .createNamedQuery("CITIES.findById", CitiesEntity.class)
                .setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public List<CitiesEntity> findByName(String name) {
        TypedQuery<CitiesEntity> query = Manager.getInstance().getEntityManager()
                .createNamedQuery("CITIES.findByName", CitiesEntity.class)
                .setParameter("name", name);

        return query.getResultList();
    }

}
