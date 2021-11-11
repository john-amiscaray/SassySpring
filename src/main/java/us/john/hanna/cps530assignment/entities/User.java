package us.john.hanna.cps530assignment.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity{

    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;

    @OneToMany(mappedBy = "owner")
    @Getter
    @Setter
    private Set<Todo> todos;

}
