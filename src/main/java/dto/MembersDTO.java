
package dto;

import entities.Members;


public class MembersDTO {
    private Long id;
    private String name; 
    private String favTvShow;

    public MembersDTO(Members members) {
        this.id = members.getId();
        this.name = members.getName();
        this.favTvShow = members.getFavTvShow();
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
