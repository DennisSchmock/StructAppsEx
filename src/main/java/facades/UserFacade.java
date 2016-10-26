package facades;

import com.mysql.jdbc.StringUtils;
import security.IUserFacade;
import entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.jasypt.util.password.StrongPasswordEncryptor;
import security.IUser;

public class UserFacade implements IUserFacade {

    /*When implementing your own database for this seed, you should NOT touch any of the classes in the security folder
    Make sure your new facade implements IUserFacade and keeps the name UserFacade, and that your Entity User class implements 
    IUser interface, then security should work "out of the box" with users and roles stored in your database */
    private final Map<String, IUser> users = new HashMap<>();
    private EntityManagerFactory emf;
    StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

    public UserFacade() {
        User user = new User("user", "test");
        user.addRole("User");

        users.put(user.getUserName(), user);
        User admin = new User("admin", "test");
        admin.addRole("Admin");
        users.put(admin.getUserName(), admin);

        User both = new User("user_admin", "test");
        both.addRole("User");
        both.addRole("Admin");
        users.put(both.getUserName(), both);

    }

    public UserFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public User createUser(User user) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(user);
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();

        }
        return user;
    }

    @Override
    public IUser getUserByUserId(String id) {
        System.out.println("getUserbyId" +id);
        EntityManager em = getEntityManager();
        User user = null;
        if (StringUtils.isStrictlyNumeric(id)) {
            int id1 = Integer.parseInt(id);
            try {
                user = em.find(User.class, id);
            } finally {
                em.close();
            }
        } else{
            try {
                TypedQuery<User> q = em.createNamedQuery("User.findByUserName", User.class);
                q.setParameter("userName", id);
                List<User> users = q.getResultList();
                return users.get(0);
            } finally {
                em.close();
            }
        }

        return user;
    }

    /*
  Return the Roles if users could be authenticated, otherwise null
     */
    @Override
    public List<String> authenticateUser(String userName, String password) {
        System.out.println(userName + password);
        EntityManager em = getEntityManager();
        IUser user = null;
        try {
            Query q = em.createNamedQuery("User.findByUserName");
            q.setParameter("userName", userName);
            user = (IUser) q.getSingleResult();

        } finally {
            em.close();
        }
        return user != null && user.getPassword().equals(password) ? user.getRolesAsStrings() : null;
    }

}
