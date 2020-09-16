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
    public static CarFacade getCarFacade(EntityManagerFactory _emf) {
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
            Query query2 = em.createNamedQuery("Car.getAll");

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
    
     public List<CarDTO> getCarByMake(String make) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Car.getCarByMake");
            query.setParameter("make", make);
            List<Car> carList = query.getResultList();
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
     
    public void populateDB(){
            EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            Car c1 = new Car(2020, "BMW","X5M", 500000, "Frederik");
            Car c2 = new Car(2008, "Renualt","Megane", 200, "Josef"); 
            Car c3 = new Car(2008, "Fiat","Punto", 10000, "Thor");
            Car c4 = new Car(2020, "BMW","M5", 1200000, "Frederik");
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.persist(c4);
            

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
