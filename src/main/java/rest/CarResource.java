
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CarDTO;
import facades.CarFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * A REST class to create REST endpoints for the car part of the assingment. 
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 * First path is what you will have to type behind the "https://nameofthewebsite/cars 
 */
@Path("cars")
public class CarResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final CarFacade cf = CarFacade.getCarFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Method that returns all cars in database
     * @return a list of CarDTO objects 
     * Can be accessed at https://nameofthewebsite/cars/all 
     */ 
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("all")
    public String allCars() {
        try {
            List<CarDTO> carList = cf.getAllCars();
            return GSON.toJson(carList);
        } catch (javax.persistence.NoResultException e) {
            String errorString = "The function is either not working or there might not be any data in the database. IT HAS BEEN CONTACTED";
            return GSON.toJson(errorString);
        }
    }

    /**
     * Method that shows all cars by make
     * @param make of the car youre searching for 
     * @return a list of cars with the given make
     * Can be accessed at https://nameofthewebsite/cars/bymake/makeYoureSearchingFor (eg BMW)  
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("bymake/{make}")
    public String carByMake(@PathParam("make") String make) {
        try {

            List<CarDTO> carList = cf.getCarByMake(make);
            return GSON.toJson(carList);
        } catch (javax.persistence.NoResultException e) {
            String errorString = "The make " + make + " is not in the database and therefore this program cannot show you the result";
            return GSON.toJson(errorString);
        }
    }
    
    /**
     * Method to find cars with a given price 
     * @param price price youre searching for
     * @return a list of cars with a price less than or equal to the param 
     * Can be accessed at https://nameofthewebsite/cars/byprice/priceYoureSearchingFor
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("byprice/{price}")
    public String carByPrice(@PathParam("price") int price) {
        try {
            List<CarDTO> carList = cf.getCarByPrice(price);
            return GSON.toJson(carList);
        } catch (javax.persistence.NoResultException e) {
            String errorString = "The price " + price + " is not in the database and therefore this program cannot show you the result";
            return GSON.toJson(errorString);
        }
    }

    /**
     * @return a String to tell that everything went all right or throws an exception. 
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("populate")
    public String populateDB() {
        try{
        cf.populateDB();
        return "{\"msg\":\"3 cars added\"}";
        } catch (Exception e) {
            String errorString = "The populate method could not be run. Nothing has been inserted into the database";
            return GSON.toJson(errorString);
        }
    }
    
}
