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
}
