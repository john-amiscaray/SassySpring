package us.john.hanna.cps530assignment.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import us.john.hanna.cps530assignment.data.UserRepo;
import us.john.hanna.cps530assignment.domain.LoginRequest;
import us.john.hanna.cps530assignment.domain.SignupRequest;
import us.john.hanna.cps530assignment.entities.User;
import us.john.hanna.cps530assignment.exceptions.BadAuthRequest;

import java.util.Date;
import java.util.HashSet;

@Service
public class JWTAuthService implements AuthService{

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authManager;

    @Value("app.secret")
    private String secret;

    public JWTAuthService(UserRepo userRepo, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService,
                          AuthenticationManager authManager) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.authManager = authManager;
    }

    @Override
    public void signUp(SignupRequest request) throws BadAuthRequest {
        if(!request.getPassword().equals(request.getConfirmPassword())){
            throw new BadAuthRequest("passwords do not match");
        }
        User user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()), new HashSet<>());
        userRepo.save(user);
    }

    @Override
    public String login(LoginRequest request) {
        UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword(), user.getAuthorities()));

        final long TEN_HOURS = 36000000L;
        return JWT.create()
                // subject is something to identify the user
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TEN_HOURS))
                // sign the token with our secret key so we can identify legit tokens
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    @Override
    public User getCurrentlySignedInUser() throws BadAuthRequest {
        // Get an object containing the username and password of the current user
        UsernamePasswordAuthenticationToken auth =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        return userRepo.findByUsername((String) auth.getPrincipal()).orElseThrow(() -> new BadAuthRequest("You are not signed in"));
    }

    @Override
    public UsernamePasswordAuthenticationToken verifyToken(String token) {
        // Check the token is legit using our secret key and then get its subject (the username of the user)
        String username = JWT.require(Algorithm.HMAC512(secret.getBytes()))
                .build()
                .verify(token).getSubject();
        if(username == null){
            throw new BadCredentialsException("Could not verify token");
        }

        UserDetails details = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(
                details.getUsername(),
                details.getPassword(), details.getAuthorities()
        );
    }

}
