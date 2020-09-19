package dto;

import entities.Joke;

/**
 * JokeDTO class is a "wrapper" class to store Joke objects.
 * @author Josef
 */
public class JokeDTO {
    
    private String theJoke;
    private String reference;
    private String type;
    
    /**
     * A JokeDTO Object has 3 global variables: theJoke, reference, type. 
     * @param Joke
     */

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
