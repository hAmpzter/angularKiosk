package Resources;

import auth.User;
import com.github.toastshaman.dropwizard.auth.jwt.hmac.HmacSHA512Signer;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebToken;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenClaim;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenHeader;
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

    public LoginResource(byte[] tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    @POST
    public Response login(LoginUser user) {
        if (user.username.equals("bobo") && user.password.equals("mjao")) {
            final HmacSHA512Signer signer = new HmacSHA512Signer(tokenSecret);
            final JsonWebToken token = JsonWebToken.builder()
                    .header(JsonWebTokenHeader.HS512())
                    .claim(JsonWebTokenClaim.builder()
                            .subject("good-guy")
                            .issuedAt(DateTime.now())
                            .build())
                    .build();
            final String signedToken = signer.sign(token);
            return Response.ok(new User(user.username,signedToken)).header("jwtToken", signedToken).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();

    }

    @GET
    @PermitAll
    @Path("/with-permit")
    public Response withPermit() {
        return Response.ok().build();
    }

    @GET
    @Path("/no-permit")
    public Response noPermit() {
        return Response.ok().build();
    }
}
