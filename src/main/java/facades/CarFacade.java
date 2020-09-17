
package facades;

import dto.CarDTO;
import entities.Car;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 * The CarFacade class is the class in charge of all operations with the database. 
 * Here we use the namedqueries created in the entity class Car.java
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class CarFacade {

    private static CarFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CarFacade() {
    }

    /**
     * Method to get an instance of the carfacade class (aka this class). 
     * @param _emf an entityManagerFactory which is used for using the entity classes and doing Database operations from these. 
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

    /**
     * Calls the namedquery from car entity class and removes all data in databases. 
     */
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

    /**
     * Calls the namedquery from car entity class and gets all data in databases. 
     * @return a list of CarDTO objects, carDTO will hide "sensitive" information on the website. 
     */
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
    
    /**
     * Method to find cars by a specific make (eg BMW). 
     * @param make the make that you're searching for 
     * @return a list of CarDTO objects that has something like the make you're searching for. 
     */
    public List<CarDTO> getCarByMake(String make) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Car.getCarByMake");
            query.setParameter("make", make);
            List<Car> carList = query.getResultList();
            List<CarDTO> carDTOList = new ArrayList();
            for(Car car : carList) {
                CarDTO carDTO = new CarDTO(car);
                carDTOList.add(carDTO);
            }
            return carDTOList;
        } finally {
            em.close();
        }
    }
     
    /**
     * Calls the namedquery from car entity class and finds every car with a price lower than or equal (ltoe) to the param. 
     * @param price , the price you want to find cars for, ltoe with the param. 
     * @return the list of cars with a pricer ltoe with the param. 
     */
    public List<CarDTO> getCarByPrice(int price) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Car.getCarByPrice");
            query.setParameter("price", price);
            List<Car> carList = query.getResultList();
            List<CarDTO> carDTOList = new ArrayList();
            for(Car car : carList) {
                CarDTO carDTO = new CarDTO(car);
                carDTOList.add(carDTO);
            }
            return carDTOList;
        } finally {
            em.close();
        }
    }  
     
    /**
     * Method to insert data into the database.
     */
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
