package pl.akademiakodu.miniblog.model.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"password"})
public class LoginForm {

    private String userName;
    private String password;


    //GETTERS, SETTERS, TOSTRING
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
