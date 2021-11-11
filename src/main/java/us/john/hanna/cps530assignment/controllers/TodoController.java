package us.john.hanna.cps530assignment.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.john.hanna.cps530assignment.domain.TodoDTO;

@RestController
@RequestMapping("todo")
public class TodoController {

    private final ResponseEntity<Void> notImplemented;

    public TodoController(@Qualifier("notImplemented") ResponseEntity<Void> notImplemented){

        this.notImplemented = notImplemented;

    }

    @GetMapping("/allTodos")
    public ResponseEntity<?> myTodos(){

        return notImplemented;

    }

    @PostMapping
    public ResponseEntity<?> postTodo(@RequestBody TodoDTO dto){

        return notImplemented;

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable("id") Long id){

        return notImplemented;

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable("id") Long id, @RequestBody TodoDTO dto){

        return notImplemented;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") Long id){

        return notImplemented;

    }

}
