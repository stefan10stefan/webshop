package project.domain.model.request;

public class RegistrationRequest {

    public String email;
    public String password;
    public String firstName;
    public String lastName;

    public RegistrationRequest(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
