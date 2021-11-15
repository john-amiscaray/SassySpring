package us.john.hanna.cps530assignment.services;

import us.john.hanna.cps530assignment.domain.TodoDTO;
import us.john.hanna.cps530assignment.entities.Todo;
import us.john.hanna.cps530assignment.exceptions.TodoNotFoundException;

import java.util.Set;

public interface TodoService {

    Set<Todo> getAllTodos();

    Todo getTodoById() throws TodoNotFoundException;

    void deleteTodo() throws TodoNotFoundException;

    Long updateTodo(Long id, TodoDTO dto) throws TodoNotFoundException;

    Long createTodo(TodoDTO dto);

}
