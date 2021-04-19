package com.petstore.demo.model;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    private double price;

    public Pet(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Pet(int id, String name, String type, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Pet() {
    }

}
