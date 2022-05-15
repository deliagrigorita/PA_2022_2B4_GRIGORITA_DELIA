package app.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COUNTRIES", schema = "STUDENT")
@NamedQueries({
        @NamedQuery(name = "Countries.findById",
                query = "select e from CountriesEntity e where e.id = :id"),
        @NamedQuery(name = "Countries.findByName",
                query = "select e from CountriesEntity e where e.name = :name")
})

public class CountriesEntity{
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private List<CitiesEntity> CitiesItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="continent_id",nullable=false)
    public ContinentsEntity continent;

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

        CountriesEntity that = (CountriesEntity) o;

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
