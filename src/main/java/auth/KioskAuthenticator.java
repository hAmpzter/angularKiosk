package auth;

import com.github.toastshaman.dropwizard.auth.jwt.JsonWebTokenValidator;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebToken;
import com.github.toastshaman.dropwizard.auth.jwt.validator.ExpiryValidator;
import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import java.security.Principal;

public class KioskAuthenticator implements Authenticator<JsonWebToken, Principal> {

    final JsonWebTokenValidator expiryValidator = new ExpiryValidator();
    @Override
    public Optional<Principal> authenticate(JsonWebToken jsonWebToken) throws AuthenticationException {
        expiryValidator.validate(jsonWebToken);
        if ("good-guy".equals(jsonWebToken.claim().subject())) {
            return Optional.of(new User(jsonWebToken.claim().subject(), jsonWebToken.deserialize()));
        }
        return Optional.absent();

    }
}

