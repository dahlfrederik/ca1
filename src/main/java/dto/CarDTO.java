package dto;

import entities.Car;

/**
 * CarDTO class is a "wrapper" class to store car objects without all their "sensitive/private" values, like owner. 
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class CarDTO {
    private String make; 
    private String model; 
    private int year;
    private int price; 
    
    /**
     * A carDTO Object has 4 global variables: Make, model, year and price. 
     * @param car
     */
    public CarDTO(Car car){
        this.make = car.getMake(); 
        this.model = car.getModel(); 
        this.year = car.getYear();
        this.price = car.getPrice(); 
    }

    /**
     *
     * @return the make of the car 
     */
    public String getMake() {
        return make;
    }

    /**
     * Sets a given make to the car 
     * @param make - the make you want to change for the object. 
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     *
     * @return model of the car 
     */
    public String getModel() {
        return model;
    }

    /**
     * Set model of a car 
     * @param model- the model you want to change for the object. 
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * This returns the year which the car was made in 
     * @return the cars year
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year for the car
     * @param year - the year you want to change for the object. 
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     *
     * @return price of the car 
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the car 
     * @param price - the price you want to change for the object. 
     */
    public void setPrice(int price) {
        this.price = price;
    }
    
    
}
