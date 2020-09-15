/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CarDTO;
import entities.Car;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class CarFacade {

    private static CarFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CarFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CarFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void deleteAllCars() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<CarDTO> getAllCars() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query2 = em.createNamedQuery("Movie.getAll");

            List<Car> carList = query2.getResultList();
            List<CarDTO> carDTOList = new ArrayList();
            for(Car car : carList) {
                CarDTO movDTO = new CarDTO(car);
                carDTOList.add(movDTO);
            }

            return carDTOList;
        } finally {
            em.close();
        }
    }
    
     public CarDTO getCarByMake(String make) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Car.getCarByMake");
            query.setParameter("make", make);
            Car car = (Car) query.getSingleResult();
            CarDTO carDTO = new CarDTO(car); 

            return carDTO;
        } finally {
            em.close();
        }
    }
}
