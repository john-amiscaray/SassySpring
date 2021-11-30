package us.john.hanna.cps530assignment.services;

import us.john.hanna.cps530assignment.domain.TodoDto;
import us.john.hanna.cps530assignment.entities.Todo;
import us.john.hanna.cps530assignment.exceptions.BadAuthRequest;
import us.john.hanna.cps530assignment.exceptions.TodoNotFoundException;

import java.util.Set;

public interface TodoService {

    Set<Todo> getAllTodos() throws BadAuthRequest;

    Todo getTodoById(Long id) throws TodoNotFoundException, BadAuthRequest;

    void deleteTodo(Long id) throws TodoNotFoundException, BadAuthRequest;

    void updateTodo(Long id, TodoDto dto) throws TodoNotFoundException, BadAuthRequest;

    Long createTodo(TodoDto dto) throws BadAuthRequest;

}
