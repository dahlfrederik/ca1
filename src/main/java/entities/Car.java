

package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * A car entity class containing all information about a car-object. 
 * The entity class can be linked directly with the database upon running the program. 
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 * The entity class starts with some namedqueries that is used to determine what methods we will probably use later on.   
 */
@Entity
@NamedQueries({
@NamedQuery(name = "Car.deleteAllRows", query = "DELETE from Car"),
@NamedQuery(name = "Car.getAll", query = "SELECT c from Car c"),
@NamedQuery(name = "Car.getCarByMake", query = "SELECT c from Car c WHERE c.make LIKE :make"),
@NamedQuery(name = "Car.getCarByPrice", query = "SELECT c from Car c WHERE c.price <= :price"),
@NamedQuery(name = "Car.getCarById", query = "SELECT c from Car c WHERE c.id LIKE :id"),
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

    /**
     * Sets values into all the global variables of a car entity object. 
     * @param year the car where made 
     * @param make the make / brand of the car 
     * @param model the model of the car
     * @param price the price of the car 
     * @param owner the owner of the car 
     */
    public Car(int year, String make, String model, int price, String owner){
        this.year = year; 
        this.make = make;
        this.model = model; 
        this.price = price; 
        this.owner = owner; 
    }
    
    /**
     * There has to be an empty constructor when using an entity class. 
     */
    public Car() {
    }

    /**
     * @return the id of a car
     */
    public int getId() {
        return id;
    }

    /**
     * Sets a new ID for a car object 
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return the year of a car
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets a new year for a car object 
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     *
     * @return the make of a car
     */
    public String getMake() {
        return make;
    }

    /**
     * Sets a new make for a car object 
     * @param make
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     *
     * @return the model of a car
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets a new model for a car object 
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     *
     * @return the price of a car
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets a new price for a car object 
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    
 
}
