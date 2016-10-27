package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import security.IUser;

@Entity
@Table(name = "iUser")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.userName = :userName")
})

public class User implements IUser, Serializable {

    private String password;  //Pleeeeease dont store me in plain text

    @Id
    private String userName;
    
    
    @OneToMany (cascade = CascadeType.PERSIST)
    List<Role> roles = new ArrayList();

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, List<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public void addRole(Role role) {
        
        roles.add(role);
    }

    @Override
    public List<String> getRolesAsStrings() {
        List<String> rolesString = new ArrayList<>();
        for (Role role : roles) {
            rolesString.add(role.getRole());
        }
        for (String string : rolesString) {
            System.out.println(string);
        }
        return rolesString;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
