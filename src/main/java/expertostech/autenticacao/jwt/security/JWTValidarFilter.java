package expertostech.autenticacao.jwt.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTValidarFilter extends BasicAuthenticationFilter{

    public static final String HEADER_ATRIBUTO = "Authorization";
    public static final String ATRIBUTO_PREFIXO = "Bearer ";

    public JWTValidarFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }




    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        System.out.println("********* Passou get cookie");

        Cookie token = WebUtils.getCookie(request, "token");


        if (token == null){
            System.out.println("***** token nullo");
            chain.doFilter(request, response);
            return;
        }

        String jwt = token.getValue();
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(jwt, response);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token, HttpServletResponse response) throws IOException {
        try {
            String login = JWT.require(Algorithm.HMAC512(JWTAutenticarFilter.TOKEN_SENHA))
                    .build()
                    .verify(token)
                    .getClaim("login").asString();
            if (login == null) {
                return null;
            }
            return new UsernamePasswordAuthenticationToken(login,null, new ArrayList<>());
        } catch (JWTVerificationException e){
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            return null;
        }

    }

}
