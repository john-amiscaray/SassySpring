package us.john.hanna.cps530assignment.services;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import us.john.hanna.cps530assignment.data.UserRepo;
import us.john.hanna.cps530assignment.entities.User;

@Service
@AllArgsConstructor
public class AuthManagerImpl implements AuthenticationManager {

    private UserRepo userRepo;
    private PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        /*
        The principal and credentials fields in the Authentication object are generic objects, but we used
        usernames as the principal and passwords as the credentials, so we must cast them to strings.
         */
        User user = userRepo.findByUsername(
                (String) authentication.getPrincipal()).orElseThrow(() -> new BadCredentialsException("Could not find the user"));
        if(encoder.matches((String) authentication.getCredentials(), user.getPassword())){

            return authentication;

        }

        return null;
    }
}
