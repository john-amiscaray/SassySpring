package me.john.amiscaray.cps350assignment.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
public class Todo extends BaseEntity {

    @Getter
    @Setter
    private String subject;
    @Getter
    @Setter
    private Timestamp dueDate;

    @ManyToOne
    private User owner;


}
