package us.john.hanna.cps530assignment.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.john.hanna.cps530assignment.entities.Todo;
import us.john.hanna.cps530assignment.entities.User;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long> {

    Set<Todo> findAllByOwner(User owner);

    Optional<Todo> findByOwnerAndId(User owner, Long id);

    Boolean existsByOwnerAndId(User user, Long id);

}
