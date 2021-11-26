package us.john.hanna.cps530assignment.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import us.john.hanna.cps530assignment.data.TodoRepo;
import us.john.hanna.cps530assignment.domain.TodoDto;
import us.john.hanna.cps530assignment.entities.Todo;
import us.john.hanna.cps530assignment.exceptions.BadAuthRequest;
import us.john.hanna.cps530assignment.exceptions.TodoNotFoundException;

import java.sql.Timestamp;
import java.util.Set;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepo todoRepo;
    private AuthService authService;

    @Override
    public Set<Todo> getAllTodos() throws BadAuthRequest {
        return todoRepo.findAllByOwner(authService.getCurrentlySignedInUser());
    }

    @Override
    public Todo getTodoById(Long id) throws TodoNotFoundException, BadAuthRequest {
        return todoRepo.findByOwnerAndId(authService.getCurrentlySignedInUser(), id).orElseThrow(TodoNotFoundException::new);
    }

    @Override
    public void deleteTodo(Long id) throws TodoNotFoundException, BadAuthRequest {
        if(!todoRepo.existsByOwnerAndId(authService.getCurrentlySignedInUser(), id)){
            throw new TodoNotFoundException();
        }
        todoRepo.deleteById(id);
    }

    @Override
    public Long updateTodo(Long id, TodoDto dto) throws TodoNotFoundException, BadAuthRequest {

        Todo original = todoRepo.findByOwnerAndId(authService.getCurrentlySignedInUser(), id)
                .orElseThrow(TodoNotFoundException::new);
        Timestamp due = dto.getDueDate() == null ? original.getDueDate() : new Timestamp(dto.getDueDate());
        String subject = dto.getSubject() == null ? original.getSubject() : dto.getSubject();
        Todo todo = todoRepo.save(new Todo(id, subject, due,
                authService.getCurrentlySignedInUser()));
        return todo.getId();
    }

    @Override
    public Long createTodo(TodoDto dto) throws BadAuthRequest {

        Todo todo = todoRepo.save(new Todo(dto.getSubject(), new Timestamp(dto.getDueDate()),
                authService.getCurrentlySignedInUser()));
        return todo.getId();
    }
}
