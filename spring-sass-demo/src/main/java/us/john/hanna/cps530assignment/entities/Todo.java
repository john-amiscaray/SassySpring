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

    @Getter
    @Setter
    private String subject;
    @Getter
    @Setter
    private Timestamp dueDate;

    @ManyToOne
    private User owner;

    public Todo(Long id, String subject, Timestamp dueDate, User owner) {
        this.subject = subject;
        this.dueDate = dueDate;
        this.owner = owner;
        this.setId(id);
    }

    public TodoDto toDto(){

        return new TodoDto(getId(), dueDate.getTime(), subject);

    }

}
