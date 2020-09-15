package facades;

import dto.CarDTO;
import entities.Car;
import utils.EMF_Creator;
import entities.RenameMe;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class CarFacadeTest {

    private static EntityManagerFactory emf;
    private static CarFacade cf;

    public CarFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       cf = CarFacade.getCarFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    
    //int year, String make, String model, int price, String owner
    
    
    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            Car c1 = new Car(2020, "BMW","X5M", 500000, "Frederik");
            Car c2 = new Car(2008, "Renualt","Megane", 200, "Josef"); 
            Car c3 = new Car(2008, "Fiat","Punto", 10000, "Thor");
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    
    @Test
    public void testAFacadeMethod() {
        assertEquals(3, cf.getAllCars().size(), "Expects three rows in the database");
    }
    
    @Test
    public void testGetCarByMake(){
        CarDTO car = cf.getCarByMake("BMW"); 
        Car c1 = new Car(2020, "BMW","X5M", 500000, "Frederik"); 
        CarDTO expected = new CarDTO(c1); 
        
        assertEquals(expected.getMake(), car.getMake()); 
        
    }
    

}
