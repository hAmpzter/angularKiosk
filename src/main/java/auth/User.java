package auth;


import javax.security.auth.Subject;
import java.security.Principal;

public class User implements Principal {

    public String name;
    public String token;

    public User(String name, String token) {
        this.name = name;
        this.token = token;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public boolean implies(Subject subject) {
        return false;
    }
}
