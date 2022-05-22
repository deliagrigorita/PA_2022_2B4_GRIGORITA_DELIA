package com.example.lab11.entities;

import javax.persistence.*;
import java.io.Serializable;

//Un obiect instantiat cu aceasta clasa este echivalentul unei linii din tabela users din baza de date.

@Entity
@Table(name = "USERS", schema = "STUDENT")
public class User implements Serializable {
    private short idUser;
    private String name;

    @Id
    @Column(name = "ID_USER")
    public short getIdUser() {
        return idUser;
    }

    public void setIdUser(short idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
