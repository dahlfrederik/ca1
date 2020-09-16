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

@Path("joke")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final JokeFacade FACADE = JokeFacade.getJokeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllJokes() {
        EntityManager em = EMF.createEntityManager();
        try {
            List<JokeDTO> jokes = FACADE.getAllJoke();
            return GSON.toJson(jokes);
        } finally {
            em.close();
        }
    }

    @GET
    @Path("id/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeById(@PathParam("id") Long id) {
        EntityManager em = EMF.createEntityManager();
        try {
            Joke joke = FACADE.getJokeById(id);
            return GSON.toJson(joke);
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        FACADE.populateDB();
        return "{\"msg\":\"Jokes Added!\"}";
    }

    @GET
    @Path("/unpopulate")
    @Produces({MediaType.APPLICATION_JSON})
    public String unpopulate() {
        FACADE.deleteAllJokes();
        return "{\"msg\":\"All jokes removed\"}";
    }

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
