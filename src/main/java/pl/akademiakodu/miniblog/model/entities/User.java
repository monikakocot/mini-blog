package pl.akademiakodu.miniblog.model.entities;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String email;
    //@JasonIgnore - in the case we dont have DTOs we need to add this to not sell User's password to client
    private String password;

/*@Embedded // Nobody gonna modify etc. user
    private AuditEntity audit = new AuditEntity();

    public AuditEntity getAudit() {return audit;}
    public void setAudit(AuditEntity audit) {
        this.audit = audit;
    }
*/
//GETTERS, SETTERS, TO STRING
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
