package facades;

import dto.JokeDTO;
import entities.Joke;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class JokeFacade {

    private static JokeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private JokeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokeFacade getJokeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public long getJokeCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long jokeCount = (long) em.createQuery("SELECT COUNT(j) FROM Joke j").getSingleResult();
            return jokeCount;
        } finally {
            em.close();
        }
    }

    public List<JokeDTO> getAllJoke() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<JokeDTO> query
                    = (TypedQuery<JokeDTO>) em.createNamedQuery("Joke.getAll");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Joke getJokeById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Joke.getJokeById");
            query.setParameter("id", id);
            return (Joke) query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public void deleteAllJokes() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Joke getRandomJoke() {
        EntityManager em = emf.createEntityManager();
        try {
            JokeFacade JF = new JokeFacade();
            Joke randomJoke;
            List jokes = JF.getAllJoke();
            int randomNr = (int) (Math.random() * jokes.size());
            randomJoke = (Joke) jokes.get(randomNr);
            return randomJoke;
        } finally {
            em.close();
        }
    }

    public void populateDB() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Joke("Hvorfor var blondinen glad for, at samle et puzzlespil på 6 måneder?"
                    + "– fordi der stod 2-4 år", "vitser-jokes.dk", "Blondie Jokes"));
            em.persist(new Joke("Hvad Kalder man en blondine med en hjerne?" + "– Uddød"
                    + "", "vitser-jokes.dk", "Blondie Jokes"));
            em.persist(new Joke("Hvorfor lever kvinder længer end mænd?" + "– Fordi de lige skal tale færdig"
                    + "", "vitser-jokes.dk", "Kvinde Jokes"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
