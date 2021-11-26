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

        User user = userRepo.findByUsername(
                (String) authentication.getPrincipal()).orElseThrow(() -> new BadCredentialsException("Could not find the user"));
        if(encoder.matches((String) authentication.getCredentials(), user.getPassword())){

            return authentication;

        }

        return null;
    }
}
