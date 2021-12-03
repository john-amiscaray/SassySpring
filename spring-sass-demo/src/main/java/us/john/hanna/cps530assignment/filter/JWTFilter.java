package us.john.hanna.cps530assignment.filter;

import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import us.john.hanna.cps530assignment.exceptions.BadAuthRequest;
import us.john.hanna.cps530assignment.services.AuthService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends BasicAuthenticationFilter {

    private final AuthService authService;

    public JWTFilter(AuthenticationManager authenticationManager, AuthService authService) {
        super(authenticationManager);
        this.authService = authService;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Get Authorization header
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader == null){

            String url = request.getRequestURL().toString();
            System.out.println("You are at " + url);
            throw new BadAuthRequest("Missing auth token");

        }else if(!authorizationHeader.startsWith("Bearer")){

            throw new BadAuthRequest("Authorization header should have *Bearer* prefix before the token.");

        }
        // Remove the "Bearer" prefix
        String token = authorizationHeader.substring(7);
        // Verify token
        UsernamePasswordAuthenticationToken auth = authService.verifyToken(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        // send request through next filter
        chain.doFilter(request, response);
    }
}
