package us.john.hanna.cps530assignment.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import us.john.hanna.cps530assignment.data.UserRepo;
import us.john.hanna.cps530assignment.domain.UserDetailsImpl;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsImpl(userRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User with that username not found")));
    }
}
