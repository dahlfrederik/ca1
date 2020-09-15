package dto;

import entities.Joke;

/**
 *
 * @author josef
 */
public class JokeDTO {
    
    private String theJoke;
    private String reference;
    private String type;

    public JokeDTO(Joke joke) {
        this.theJoke = joke.getTheJoke();
        this.reference = joke.getReference();
        this.type = joke.getType();
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
