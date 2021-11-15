package us.john.hanna.cps530assignment.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import us.john.hanna.cps530assignment.data.UserRepo;
import us.john.hanna.cps530assignment.domain.LoginRequest;
import us.john.hanna.cps530assignment.domain.SignupRequest;
import us.john.hanna.cps530assignment.entities.User;
import us.john.hanna.cps530assignment.exceptions.BadAuthRequest;

import java.util.HashSet;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    private UserRepo userRepo;

    @Override
    public void signUp(SignupRequest request) throws BadAuthRequest {
        if(!request.getPassword().equals(request.getConfirmPassword())){
            throw new BadAuthRequest("passwords do not match");
        }
        User user = new User(request.getUsername(), request.getPassword(), new HashSet<>());
        userRepo.save(user);
    }

    @Override
    public String login(LoginRequest request) {
        return null;
    }

    @Override
    public User getCurrentlySignedInUser() {
        return null;
    }

}
