package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MembersDTO;
import utils.EMF_Creator;
import facades.MembersFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * A REST class to create REST endpoints for the member part of the assingment. 
 * @author Thor Christensen
 */

@Path("groupmembers")
public class MemberResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final MembersFacade FACADE =  MembersFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    //Method used to return all members from database i form of JSON
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMembers() {
        EntityManager em = EMF.createEntityManager();
        try {
            MembersFacade mf = MembersFacade.getFacadeExample(EMF);
            List<MembersDTO> membersList = mf.getAllMembers();
            return GSON.toJson(membersList);
        } finally {
            em.close();
        }
    }
    
    //Method used to return member by id from database i form of JSON
    @GET
    @Path("id/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMembersById(@PathParam("id") Long id) {
        EntityManager em = EMF.createEntityManager();
        try {
            MembersFacade mf = MembersFacade.getFacadeExample(EMF);
            MembersDTO dtoMov = mf.getMemberById(id);
            return GSON.toJson(dtoMov);
        } finally {
            em.close();
        }
    }
    
    
    //Method used to return member by name from database i form of JSON
    @Path("name/{name}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMembersByName(@PathParam("name") String name) {
        EntityManager em = EMF.createEntityManager();
        try {
            MembersFacade mf = MembersFacade.getFacadeExample(EMF);
            MembersDTO dtoMem = mf.getMemberByName(name);
            return GSON.toJson(dtoMem);
        } finally {
            em.close();
        }
    }
    
    //Method used to populate the database with members
    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populate(){
        FACADE.populateDB();
        return "{\"msg\":\"Group members added\"}";
    }

}
