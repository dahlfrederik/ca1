package facades;

import entities.Joke;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
            long renameMeCount = (long) em.createQuery("SELECT COUNT(j) FROM Joke j").getSingleResult();
            return renameMeCount;
        } finally {
            em.close();
        }

    }

    public List<Joke> getAllJoke() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Joke> query
                    = em.createQuery("Select m from Movie m", Joke.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Joke getMovieById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("Joke.getJokeById");
            query.setParameter("id", id);
            Joke joke = (Joke) query.getSingleResult();
            return joke;
        } finally {
            em.close();
        }
    }

}
