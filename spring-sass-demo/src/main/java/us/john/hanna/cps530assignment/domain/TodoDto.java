package us.john.hanna.cps530assignment.domain;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {

    private Long id;
    private Long dueDate;
    private String subject;

}
