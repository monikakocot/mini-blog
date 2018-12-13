package pl.akademiakodu.miniblog.model.forms;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Getter
@Setter
@ToString(exclude = {"password"})
public class RegisterForm {

    @Size(min = 6, message = "The user's name must have a minimum of {min} characters. The phrase given ${validatedValue} does not meet the given conditions")
    private String userName;

    @Pattern(regexp = "[A-z0-9.]+@[a-z0-9\\-]+\\.[a-z]{2,5}", message = "Enter a valid email.")
    private String email;

    @Size(min = 6, max = 10, message = "The password must be between {min} and {max} characters.")
    private String password;


    //GETTERS, SETTERS, TOSTRING
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
