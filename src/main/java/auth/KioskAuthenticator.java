package auth;

import com.github.toastshaman.dropwizard.auth.jwt.JsonWebTokenValidator;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebToken;
import com.github.toastshaman.dropwizard.auth.jwt.validator.ExpiryValidator;
import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import jdbi.LoginDao;

import java.security.Principal;

public class KioskAuthenticator implements Authenticator<JsonWebToken, Principal> {
    LoginDao loginDao;

    public KioskAuthenticator(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    final JsonWebTokenValidator expiryValidator = new ExpiryValidator();
    @Override
    public Optional<Principal> authenticate(JsonWebToken jsonWebToken) throws AuthenticationException {
        expiryValidator.validate(jsonWebToken);
        String[] userNameAndPassword = jsonWebToken.claim().subject().split(":");
        if (loginDao.login(userNameAndPassword[0],userNameAndPassword[1]) != null) {
            return Optional.of(new User(jsonWebToken.claim().subject(), jsonWebToken.deserialize()));
        }
        return Optional.absent();

    }
}

