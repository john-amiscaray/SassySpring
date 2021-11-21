package us.john.hanna.cps530assignment.services;

import us.john.hanna.cps530assignment.domain.TodoDTO;
import us.john.hanna.cps530assignment.entities.Todo;
import us.john.hanna.cps530assignment.exceptions.BadAuthRequest;
import us.john.hanna.cps530assignment.exceptions.TodoNotFoundException;

import java.util.Set;

public interface TodoService {

    Set<Todo> getAllTodos() throws BadAuthRequest;

    Todo getTodoById(Long id) throws TodoNotFoundException;

    void deleteTodo(Long id) throws TodoNotFoundException;

    Long updateTodo(Long id, TodoDTO dto) throws TodoNotFoundException, BadAuthRequest;

    Long createTodo(TodoDTO dto) throws BadAuthRequest;

}
