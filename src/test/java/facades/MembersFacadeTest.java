package facades;

import utils.EMF_Creator;
import entities.Members;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MembersFacadeTest {

    private static EntityManagerFactory emf;
    private static MembersFacade facade;

    public MembersFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = MembersFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        Members m1 = new Members("Frederik Dahl", "Greys hvide verden");
        Members m2 = new Members("Josef Marc", "Vikings");
        Members m3 = new Members("Thor Christensen", "GOT");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Members.deleteAllRows").executeUpdate();
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
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
    public void testGetMemberByName() {
        MembersFacade mf = MembersFacade.getFacadeExample(emf);
        String name = "Frederik Dahl";
        String expResult = "Frederik Dahl";
        String result = mf.getMemberByName(name).getName();

        assertEquals(expResult, result);
    }
    
    @Test
    public void testgetAllMembers() {
        MembersFacade mf = MembersFacade.getFacadeExample(emf);
        int expResult = 3;
        int result = mf.getAllMembers().size();
        assertEquals(expResult, result);
    }
        



}
