package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class continents {

    @Id
    @GeneratedValue
    private Long id;
    private String name;


    public continents() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setFirstName(String blahName) {
        this.name = blahName;
    }
}
