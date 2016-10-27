/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Role;
import entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import security.IUserFacade;

/**
 *
 * @author dennisschmock
 */
public class testFacade {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    public static void main(String[] args) {
        UserFacade uf = new UserFacade(emf);
        Role userRole = new Role("User");
        Role adminRole = new Role("Admin");
        
        User user = new User("user", "123456789");
        User admin = new User("admin", "test");
        
        admin.addRole(adminRole);
        admin.addRole(userRole);
                user.addRole(userRole);

        uf.createUser(user);
        uf.createUser(admin);
        

    }

}
