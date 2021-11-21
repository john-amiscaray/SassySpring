package us.john.hanna.cps530assignment.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.john.hanna.cps530assignment.domain.TodoDto;
import us.john.hanna.cps530assignment.entities.Todo;
import us.john.hanna.cps530assignment.exceptions.BadAuthRequest;
import us.john.hanna.cps530assignment.services.TodoService;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
@AllArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/allTodos")
    public ResponseEntity<Set<TodoDto>> myTodos() throws BadAuthRequest {

        return ResponseEntity.ok(todoService.getAllTodos().stream()
                .map(Todo::toDto)
                .collect(Collectors.toSet()));

    }

    @PostMapping
    public ResponseEntity<Void> postTodo(@RequestBody TodoDto dto) throws BadAuthRequest {

        Long todoId = todoService.createTodo(dto);
        return ResponseEntity.created(URI.create("/todo/" + todoId)).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable("id") Long id) throws BadAuthRequest {

        return ResponseEntity.ok(todoService.getTodoById(id).toDto());

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateTodo(@PathVariable("id") Long id, @RequestBody TodoDto dto) throws BadAuthRequest {

        todoService.updateTodo(id, dto);
        return ResponseEntity.created(URI.create("/todo/" + id)).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") Long id) throws BadAuthRequest {

        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();

    }

}
