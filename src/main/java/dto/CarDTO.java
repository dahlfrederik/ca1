/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dto;

import entities.Car;

/**
 * 
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class CarDTO {
    private String make; 
    private String model; 
    private int year;
    private int price; 
    
    public CarDTO(Car car){
        this.make = car.getMake(); 
        this.model = car.getModel(); 
        this.year = car.getYear();
        this.price = car.getPrice(); 
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
}
