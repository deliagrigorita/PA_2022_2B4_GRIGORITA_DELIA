package app.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CONTINENTS", schema = "STUDENT")
@NamedQueries({
        @NamedQuery(name = "CONTINENTS.findById",
                query = "select e from ContinentsEntity e where e.id = :id"),
        @NamedQuery(name = "CONTINENTS.findByName",
                query = "select e from ContinentsEntity e where e.name = :name")
})
public class ContinentsEntity {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "continent")
    public List<CountriesEntity> countries;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "NAME")
    private String name;
    @Id
    @Column(name = "ID")
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContinentsEntity that = (ContinentsEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
