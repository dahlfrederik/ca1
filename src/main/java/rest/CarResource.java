/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * 
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class CarResource {
    
     private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final CarFacade FACADE =  CarFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("all")
    public String allCars() {
        try{
            CarFacade cf = CarFacade.getFacadeExample(EMF); 
            List<CarDTO> carList = cf.getAllCars(); 
            return GSON.toJson(carList);
        }catch (javax.persistence.NoResultException e) {
            String errorString = "The function is either not working or there might not be any data in the database. IT HAS BEEN CONTACTED";
            return GSON.toJson(errorString);
        }
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("byname/{make}")
    public String carByMake(@PathParam("make") String make) {
        try{
            CarFacade cf = CarFacade.getFacadeExample(EMF); 
            CarDTO car = cf.getCarByMake(make); 
                            
            return GSON.toJson(car);
        } catch (javax.persistence.NoResultException e) {
            String errorString = "The make " + make + " is not in the database and therefore this program cannot show you the result";
            return GSON.toJson(errorString);
        }
    }
}
