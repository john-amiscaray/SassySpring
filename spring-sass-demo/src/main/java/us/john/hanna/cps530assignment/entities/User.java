package us.john.hanna.cps530assignment.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity{

    @Getter
    @Setter
    // No user can have the same username
    @Column(unique = true)
    private String username;
    @Getter
    @Setter
    private String password;

    /*
     One user will own many todos. Map the entity-relationship based on the *owner* field in the Todo entity.
     This adds the id of the user as a field on the Todo table.
     */
    @OneToMany(mappedBy = "owner")
    @Getter
    @Setter
    private Set<Todo> todos;

}
