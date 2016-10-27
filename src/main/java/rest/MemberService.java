/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import facades.UserFacade;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author dennisschmock
 */
@Path("member")
@RolesAllowed("Member")
public class MemberService {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static UserFacade uf = new UserFacade(emf);
    private static Gson gson = new Gson();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MemberService
     */
    public MemberService() {
    }

    /**
     * Retrieves representation of an instance of rest.MemberService
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMemberStuff(String id) {
        JsonObject member = new JsonObject();
        member.addProperty("name", "Dennis");
        member.addProperty("mail", "dennis@schmock.eu");
        member.addProperty("quote", "Jeg klapper den, så ved den, at jeg er dens ven");

        return gson.toJson(member);
    }

    /**
     * PUT method for updating or creating an instance of MemberService
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
