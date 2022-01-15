package expertostech.autenticacao.jwt.security;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class SimpleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;



        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Headers", "content-type");
        res.addHeader("Access-Control-Allow-Headers", "access-control-allow-methods");
        res.addHeader("Access-Control-Allow-Headers", "content-type");
        res.addHeader("Access-Control-Allow-Headers", "access-control-allow-origin");
        //res.addHeader("mode", "no-cors");
        res.addHeader("Access-Control-Allow-Headers", "access-control-allow-headers");


        res.addHeader("Access-Control-Allow-Credential", "false");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST , OPTIONS, DELETE");
        res.addHeader("Access-Control-Allow-Max-Age", "3600");
        res.addHeader("Access-Control-Allow-Meaders", "Content-Type, Accept, X-Requested-With, remember-me");

        filterChain.doFilter(req, res);
    }
}
