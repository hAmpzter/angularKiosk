package auth;

import io.dropwizard.auth.Authorizer;

public class KioskAuthorizor implements Authorizer<User> {
    @Override
    public boolean authorize(User user, String role) {
        System.out.println("user: "+user.getName());
        return user.getName().equals("bobo1");
    }
}
