package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.JokeDTO;
import entities.Joke;
import facades.JokeFacade;
import utils.EMF_Creator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * A REST class to create REST endpoints for the joke part of the assingment. 
 * @author Josef
 */

@Path("joke")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final JokeFacade FACADE = JokeFacade.getJokeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Method that returns all JokeDTO from DB
     *
     * @return a list of JokeDTO objects Access at https://dachma.dk/ca1/api/joke/all
     */
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllJokes() {
        EntityManager em = EMF.createEntityManager();
        try {
            List<JokeDTO> jokes = FACADE.getAllJoke();
            return GSON.toJson(jokes);
        } catch (javax.persistence.NoResultException e) {
            String errorMsg = "This function is not working";
            return GSON.toJson(errorMsg);
        } finally {
            em.close();
        }
    }

    /**
     * Method that returns a specific Joke object from DB
     *
     * @return a Joke object Access at https://dachma.dk/ca1/api/joke/id/INSERT-ID
     */
    @GET
    @Path("id/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeById(@PathParam("id") Long id) {
        EntityManager em = EMF.createEntityManager();
        try {
            Joke joke = FACADE.getJokeById(id);
            return GSON.toJson(joke);
        } catch (javax.persistence.NoResultException e) {
            String errorMsg = "No joke with that ID " + id;
            return GSON.toJson(errorMsg);
        } finally {
            em.close();
        }
    }

    /**
     * Method to populate the DB with some hardcoded jokes
     *
     * @return void Access at https://dachma.dk/ca1/api/joke/populate
     */
    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        FACADE.populateDB();
        return "{\"msg\":\"Jokes Added!\"}";
    }

    /**
     * Method to delete all Jokes from the DB
     *
     * @return void Access at https://dachma.dk/ca1/api/joke/unpopulate
     */
    @GET
    @Path("/unpopulate")
    @Produces({MediaType.APPLICATION_JSON})
    public String unpopulate() {
        FACADE.deleteAllJokes();
        return "{\"msg\":\"All jokes removed\"}";
    }

    /**
     * Method to get a random Joke from the DB
     *
     * @return a Joke object picked randomly from Math.random
     * Access at https://dachma.dk/ca1/api/joke/randomJoke
     */
    @GET
    @Path("/randomJoke")
    @Produces({MediaType.APPLICATION_JSON})
    public String getRandomJoke() {
        EntityManager em = EMF.createEntityManager();
        try {
            Joke randomJoke;
            randomJoke = FACADE.getRandomJoke();
            return GSON.toJson(randomJoke);
        } finally {
            em.close();
        }
    }
}
