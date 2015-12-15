package com.bash.serg.persistenseplayground.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Serg Bash on 12/14/2015.
 */
@Entity
public class Inventory  implements Serializable {
    public Inventory(){
    }

    public Inventory(String brand, int year, String color){
        this.brand = brand;
        this.year = year;
        this.color = color;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 32, unique = false)
    private String brand;

    @Column(length = 4, unique = false)
    private int year;

    @Column(length = 10, unique = false)
    private String color;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
