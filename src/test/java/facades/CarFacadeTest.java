package facades;

<<<<<<< HEAD:src/test/java/facades/JokeFacadeTest.java
/**
 *
 * @author josef
 */
import entities.Joke;
=======
import dto.CarDTO;
import entities.Car;
import utils.EMF_Creator;
import entities.RenameMe;
>>>>>>> cars:src/test/java/facades/CarFacadeTest.java
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

//Uncomment the line below, to temporarily disable this test
//@Disabled
<<<<<<< HEAD:src/test/java/facades/JokeFacadeTest.java
public class JokeFacadeTest {

    private static EntityManagerFactory emf;
    private static JokeFacade facade;

    public JokeFacadeTest() {
=======
public class CarFacadeTest {

    private static EntityManagerFactory emf;
    private static CarFacade cf;

    public CarFacadeTest() {
>>>>>>> cars:src/test/java/facades/CarFacadeTest.java
    }

    @BeforeAll
    public static void setUpClass() {
<<<<<<< HEAD:src/test/java/facades/JokeFacadeTest.java
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = JokeFacade.getJokeFacade(emf);
=======
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       cf = CarFacade.getCarFacade(emf);
>>>>>>> cars:src/test/java/facades/CarFacadeTest.java
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
<<<<<<< HEAD:src/test/java/facades/JokeFacadeTest.java
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.persist(new Joke("En sjov joke", "Test person", "Sjovt"));
            em.persist(new Joke("En anden sjov joke", "Test person", "Sjovt"));
=======
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            Car c1 = new Car(2020, "BMW","X5M", 500000, "Frederik");
            Car c2 = new Car(2008, "Renualt","Megane", 200, "Josef"); 
            Car c3 = new Car(2008, "Fiat","Punto", 10000, "Thor");
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            

>>>>>>> cars:src/test/java/facades/CarFacadeTest.java
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

<<<<<<< HEAD:src/test/java/facades/JokeFacadeTest.java
    @Test
    public void testJokeCount() {
        assertEquals(2, facade.getJokeCount(), "Expects two rows in the database");
    }
    
=======
    
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
>>>>>>> cars:src/test/java/facades/CarFacadeTest.java
    

//    @Disabled
//    @Test
//        public void testGetJokeById() {
//        facade.getJokeById(1);
//        String name = "Kill Bill";
//        String expResult = "Kill Bill";
//        String result = facade.getJokeById(1).
//
//        assertEquals(expResult, result);
//    }
}
