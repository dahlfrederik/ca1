package rest;

<<<<<<< HEAD:src/test/java/rest/MembersResourceTest.java
import entities.Members;
=======
import entities.Car;
import entities.RenameMe;
>>>>>>> cars:src/test/java/rest/CarResourceTest.java
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
//Uncomment the line below, to temporarily disable this test
//@Disabled
<<<<<<< HEAD:src/test/java/rest/MembersResourceTest.java
public class MembersResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Members m1,m2, m3;
    
=======

public class CarResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Car c1, c2, c3;

>>>>>>> cars:src/test/java/rest/CarResourceTest.java
    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
<<<<<<< HEAD:src/test/java/rest/MembersResourceTest.java
        m1 = new Members("Frederik Dahl", "Greys hvide verden");
        m2 = new Members("Josef Marc", "Vikings");
        m3 = new Members("Thor Christensen", "GOT");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Members.deleteAllRows").executeUpdate();
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
=======
        c1 = new Car(2020, "BMW", "X5M", 500000, "Frederik");
        c2 = new Car(2008, "Renualt", "Megane", 200, "Josef");
        c3 = new Car(2008, "Fiat", "Punto", 10000, "Thor");

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
>>>>>>> cars:src/test/java/rest/CarResourceTest.java
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
<<<<<<< HEAD:src/test/java/rest/MembersResourceTest.java
        given().when().get("/groupmembers/all").then().statusCode(200);
    }
    
    @Test
    public void testGetAllMembers() throws Exception {
        given()
                .contentType("application/json")
                .get("/groupmembers/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
    }
    
    @Test
    public void testSpecificName() throws Exception {
        given()
                .contentType("application/json")
                .get("/groupmembers/name/Frederik Dahl").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("name", equalTo(m1.getName()));
=======
        given().when().get("/cars/all").then().statusCode(200);
    }


    @Test
    public void testGetCarBymake() throws Exception {
        given()
        .contentType("application/json")
        .get("/cars/bymake/BMW").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("make", equalTo(c1.getMake())); 
>>>>>>> cars:src/test/java/rest/CarResourceTest.java
    }

}
