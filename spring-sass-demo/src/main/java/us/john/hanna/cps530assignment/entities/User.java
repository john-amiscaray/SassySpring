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

    // One user will own many todos. Map a database table based on the *owner* field in the Todo entity
    @OneToMany(mappedBy = "owner")
    @Getter
    @Setter
    private Set<Todo> todos;

}
