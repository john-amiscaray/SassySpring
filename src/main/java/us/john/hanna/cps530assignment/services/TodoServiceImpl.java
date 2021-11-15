package us.john.hanna.cps530assignment.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import us.john.hanna.cps530assignment.data.TodoRepo;
import us.john.hanna.cps530assignment.domain.TodoDTO;
import us.john.hanna.cps530assignment.entities.Todo;
import us.john.hanna.cps530assignment.exceptions.TodoNotFoundException;

import java.sql.Timestamp;
import java.util.Set;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepo todoRepo;
    private AuthService authService;

    @Override
    public Set<Todo> getAllTodos() {
        return todoRepo.findAllByOwner(authService.getCurrentlySignedInUser());
    }

    @Override
    public Todo getTodoById(Long id) throws TodoNotFoundException {
        return todoRepo.findById(id).orElseThrow(TodoNotFoundException::new);
    }

    @Override
    public void deleteTodo(Long id) throws TodoNotFoundException {
        if(!todoRepo.existsById(id)){
            throw new TodoNotFoundException();
        }
        todoRepo.deleteById(id);
    }

    @Override
    public Long updateTodo(Long id, TodoDTO dto) throws TodoNotFoundException {
        Todo todo = todoRepo.save(new Todo(id, dto.getSubject(), new Timestamp(dto.getDueDate()),
                authService.getCurrentlySignedInUser()));
        return todo.getId();
    }

    @Override
    public Long createTodo(TodoDTO dto) {
        Todo todo = todoRepo.save(new Todo(dto.getSubject(), new Timestamp(dto.getDueDate()),
                authService.getCurrentlySignedInUser()));
        return todo.getId();
    }
}
