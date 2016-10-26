/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import facades.UserFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author lam
 */
public class UserFacadeFactory {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static UserFacade instance = new UserFacade(emf);
    public static UserFacade getInstance(){
        return instance;
    }
}
