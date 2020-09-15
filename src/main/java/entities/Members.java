package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
@NamedQuery(name = "Members.deleteAllRows", query = "DELETE from Members"),
@NamedQuery(name = "Members.getAll", query = "SELECT m FROM Members m"),
@NamedQuery(name = "Members.getByName", query = "SELECT m FROM Members m WHERE m.name LIKE CONCAT('%',:name,'%')"),
@NamedQuery(name = "Members.getById", query = "SELECT m FROM Members m WHERE m.id = :id")
})
public class Members implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; 
    private String favTvShow;
    
    public Members() {
    }

    public Members(String name, String favTvShow) {
        this.name = name;
        this.favTvShow = favTvShow;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavTvShow() {
        return favTvShow;
    }

    public void setFavTvShow(String favTvShow) {
        this.favTvShow = favTvShow;
    }
    

    
    

   
}
