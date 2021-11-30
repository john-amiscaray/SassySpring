package us.john.hanna.cps530assignment.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.john.hanna.cps530assignment.entities.User;

import java.util.Optional;

@Repository
// The type arguments must be the entity you want to query followed by the type of its ID
public interface UserRepo extends JpaRepository<User, Long> {

    // The Optional class is a wrapper class to provide null safety. It gives options for if the returned value is null.
    Optional<User> findByUsername(String username);

}
