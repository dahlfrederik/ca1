package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author josef
 */
@Entity
@NamedQueries({
@NamedQuery(name = "Joke.getAll", query = "SELECT j FROM Joke j"),
@NamedQuery(name = "Joke.getJokeById", query = "SELECT j FROM Joke j WHERE j.id LIKE :id")
})
public class Joke implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String theJoke;
    private String reference;
    private String type;

    public Joke(String theJoke, String reference, String type) {
        this.theJoke = theJoke;
        this.reference = reference;
        this.type = type;
    }

    public Joke() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheJoke() {
        return theJoke;
    }

    public void setTheJoke(String theJoke) {
        this.theJoke = theJoke;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
