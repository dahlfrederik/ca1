package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CarDTO;
import entities.Car;
import facades.CarFacade;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//Uncomment the line below, to temporarily disable this test
//@Disabled

/**
 * A test class that test the class CarResource 
 * This is testing that the REST endpoints is working as intented 
 * @author FrederikDahl
 */
public class CarResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Car c1, c2, c3;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    private static CarFacade cf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    /**
     * Is run pre everytest to start a new server, with the credentials given above. 
     */
    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        cf = CarFacade.getCarFacade(emf);

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }
    /**
     * Closes the testserver post everytest 
     */
    @AfterAll
    public static void closeTestServer() {
        
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    /**
     * Empties the database and then sets up the test database with data pre every test. 
     */
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        c1 = new Car(2020, "BMW", "X5M", 500000, "Frederik");
        c2 = new Car(2008, "Renualt", "Megane", 200, "Josef");
        c3 = new Car(2008, "Fiat", "Punto", 10000, "Thor");

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * testing if the server is up by looking at the server respons statuscode. 
     */
    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/cars/all").then().statusCode(200);
    }

    /**
     * Test if the getCarByMake is working as intended with statuscode from server and expected results.
     * @throws Exception, if an error happens. 
     */
    @Test
    public void testGetCarBymake() throws Exception {      
        List<CarDTO> carList = cf.getCarByMake("BMW"); 
        Gson GSON  =new GsonBuilder().setPrettyPrinting().create(); 
        String car = GSON.toJson(carList); 
        
        given()
        .contentType("application/json")
        .get("/cars/bymake/BMW").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("size", is(1))
                .and()
                .body(equalTo(car)); 
        }
    
    /**
     * Test if the getCarByPrice is working as intended with statuscode from server and expected results.
     * @throws Exception, if an error happens. 
     */
    @Test
    public void testGetCarPrice() throws Exception {      
        List<CarDTO> carList = cf.getCarByPrice(200); 
        Gson GSON  =new GsonBuilder().setPrettyPrinting().create(); 
        String car = GSON.toJson(carList); 
        
        given()
        .contentType("application/json")
        .get("/cars/byprice/200").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("size", is(1))
                .and()
                .body(equalTo(car)); 
        }


}
