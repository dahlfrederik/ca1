/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * 
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
@Entity
@NamedQueries({
@NamedQuery(name = "Cars.deleteAllRows", query = "DELETE from Cars"),
@NamedQuery(name = "Cars.getAll", query = "SELECT m from Cars m"),
@NamedQuery(name = "Cars.getCarByMake", query = "SELECT m from Cars m WHERE m.make LIKE :make"),
@NamedQuery(name = "Cars.getCarById", query = "SELECT m from Cars m WHERE m.id LIKE :id"),
})
public class Car implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int year;
    private String make; 
    private String model; 
    private int price; 
    private String owner; 

    public Car(int year, String make, String model, int price, String owner){
        this.year = year; 
        this.make = make;
        this.model = model; 
        this.price = price; 
        this.owner = owner; 
    }
    
    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    
 
}
