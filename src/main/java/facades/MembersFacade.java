package facades;

import dto.MembersDTO;
import entities.Members;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MembersFacade {

    private static MembersFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MembersFacade() {}
    
    
    public static MembersFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MembersFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
        public MembersDTO getMovieById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query2 = em.createQuery("Member.getById");
            query2.setParameter("id", id);
            Members members = (Members) query2.getSingleResult();
            MembersDTO memDTO = new MembersDTO(members);
            return memDTO;
        } finally {
            em.close();
        }
    }
        
    public MembersDTO getMovieByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query2 = em.createQuery("Members.getByName");
            query2.setParameter("name", name);
            Members members = (Members) query2.getSingleResult();
            MembersDTO memDTO = new MembersDTO(members);
            return memDTO;
        } finally {
            em.close();
        }
    }
    
        public List<MembersDTO> getAllMovies() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query2 = em.createQuery("Members.getAll");
            List<Members> memberList = query2.getResultList();
            List<MembersDTO> memberDTOList = new ArrayList();
            for (Members member : memberList) {
                MembersDTO memDTO = new MembersDTO(member);
                memberDTOList.add(memDTO);    
            }
            return memberDTOList;
            
        } finally {
            em.close();
        }
    }

}
