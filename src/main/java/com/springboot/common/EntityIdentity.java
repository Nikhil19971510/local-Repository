package com.springboot.common;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class EntityIdentity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
