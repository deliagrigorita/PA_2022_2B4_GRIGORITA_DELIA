package app.repository;
import app.AbstractRepository;
import app.database.Manager;
import app.entities.CountriesEntity;

import javax.persistence.TypedQuery;
import java.util.List;

public class CountryRepo implements AbstractRepository<CountriesEntity> {
    @Override
    public void save(CountriesEntity country) {
        Manager.getInstance().saveObject(country);
    }

    @Override
    public CountriesEntity findById(Long id) {
        TypedQuery<CountriesEntity> query = Manager.getInstance().getEntityManager()
                .createNamedQuery("Countries.findById", CountriesEntity.class)
                .setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public List<CountriesEntity> findByName(String name) {
        TypedQuery<CountriesEntity> query = Manager.getInstance().getEntityManager()
                .createNamedQuery("Countries.findByName", CountriesEntity.class)
                .setParameter("name", name);

        return query.getResultList();
    }

}
