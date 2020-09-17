package facades;

import dto.CarDTO;
import entities.Car;
import java.util.List;
import utils.EMF_Creator;
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

/**
 * Test class for the CarFacade 
 * @author FrederikDahl
 */
public class CarFacadeTest {

    private static EntityManagerFactory emf;
    private static CarFacade cf;

    public CarFacadeTest() {
    }

    /**
     * creates new instances of the objects pre test run
     */
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        cf = CarFacade.getCarFacade(emf);
    }

    /**
     * Supposed to clean up after the tests, but does nothing. 
     */
    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

     /**
     * Cleans and empties the test database pre everytest run and inserts data into it. 
     */
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            Car c1 = new Car(2020, "BMW", "X5M", 500000, "Frederik");
            Car c2 = new Car(2008, "Renualt", "Megane", 200, "Josef");
            Car c3 = new Car(2008, "Fiat", "Punto", 10000, "Thor");
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * this is actually done in the before part. 
     * Could actually be deleted, but we're using the startcode so will keep it. 
     */
    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    /**
     * Test that the getAllCarsMethod works 
     */
    @Test
    public void testGetAllCars() {
        assertEquals(3, cf.getAllCars().size(), "Expects three rows in the database");
    }

    /**
     * Test that the getCarByMake works as intented 
     */
    @Test
    public void testGetCarByMake() {
        List<CarDTO> carList = cf.getCarByMake("BMW");
        Car c1 = new Car(2020, "BMW", "X5M", 500000, "Frederik");
        CarDTO expected = new CarDTO(c1);
        CarDTO car = carList.get(0);

        assertEquals(expected.getMake(), car.getMake());

    }

    /**
     * Test that the getCarByPrice works as intented 
     */
    @Test
    public void testGetCarByPrice() {
        List<CarDTO> carList = cf.getCarByPrice(200);
        Car c2 = new Car(2008, "Renualt", "Megane", 200, "Josef");
        CarDTO expected = new CarDTO(c2);
        CarDTO car = carList.get(0);

        assertEquals(expected.getPrice(), car.getPrice());

    }

   

}
