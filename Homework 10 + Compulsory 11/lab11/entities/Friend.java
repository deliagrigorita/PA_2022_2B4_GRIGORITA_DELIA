package com.example.lab11.entities;

import javax.persistence.*;
import java.io.Serializable;
//Un obiect instantiat cu aceasta clasa este echivalentul unei linii din tabela friends din baza de date

@Entity
@Table(name = "FRIENDS", schema = "STUDENT")
public class Friend implements Serializable {

    private short idUser;
    private short idFriend;
    private Long id;

    @Basic
    @Id
    @Column(name = "ID_USER")
    public short getIdUser() {
        return idUser;
    }

    public void setIdUser(short idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Id
    @Column(name = "ID_FRIEND")
    public Short getIdFriend() {
        return idFriend;
    }

    public void setIdFriend(Short idFriend) {
        this.idFriend = idFriend;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
