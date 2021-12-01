package us.john.hanna.cps530assignment.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import us.john.hanna.cps530assignment.domain.TodoDto;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Todo extends BaseEntity {

    // @Getter creates a getter and @Setter creates a setter
    @Getter
    @Setter
    private String subject;
    @Getter
    @Setter
    private Timestamp dueDate;

    // Many todos will be owned by one user
    @ManyToOne
    private User owner;

    public Todo(Long id, String subject, Timestamp dueDate, User owner) {
        this.subject = subject;
        this.dueDate = dueDate;
        this.owner = owner;
        this.setId(id);
    }

    /*
    Create an Instance of TodoDto using this class. TodoDto is just a POJO that will later be used to represent HTTP responses.
    The TodoDto class simply contains an id, due date (long epoch time), and the subject of the to-do.
     */
    public TodoDto toDto(){

        return new TodoDto(getId(), dueDate.getTime(), subject);

    }

}
