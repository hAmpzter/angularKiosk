package Resources;

import auth.Hasher;
import auth.User;
import com.github.toastshaman.dropwizard.auth.jwt.hmac.HmacSHA512Signer;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebToken;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenClaim;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenHeader;
import jdbi.LoginDao;
import model.LoginUser;
import org.joda.time.DateTime;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

    private byte[] tokenSecret;
    private LoginDao loginDao;

    public LoginResource(byte[] tokenSecret, LoginDao loginDao) {
        this.tokenSecret = tokenSecret;
        this.loginDao = loginDao;
    }

    @POST
    public Response login(LoginUser user) {
        System.out.println("trying login for user:"+user.username);
        if (user.username.equals(loginDao.login(user.username, Hasher.hash(user.password)))) {
            final HmacSHA512Signer signer = new HmacSHA512Signer(tokenSecret);
            final JsonWebToken token = JsonWebToken.builder()
                    .header(JsonWebTokenHeader.HS512())
                    .claim(JsonWebTokenClaim.builder()
                            .subject(user.username+":"+Hasher.hash(user.password))
                            .issuedAt(DateTime.now())
                            .build())
                    .build();
            final String signedToken = signer.sign(token);
            System.out.println("login successfull for "+user.username);
            return Response.ok(new User(user.username,signedToken)).header("jwtToken", signedToken).build();
        }
        System.out.println("login failed for "+user.username);
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
