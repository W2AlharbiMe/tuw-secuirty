package com.example.week06d1security.Service;

import com.example.week06d1security.Api.ApiException;
import com.example.week06d1security.Model.Todo;
import com.example.week06d1security.Model.User;
import com.example.week06d1security.Repository.AuthRepository;
import com.example.week06d1security.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final AuthRepository authRepository;


    public List<Todo> findAll(User user) {
        return todoRepository.findAllByUser(user);
    }

    public Todo findById(Integer id, User user) {
        Todo todo = todoRepository.findTodoById(id);

        if(todo == null) {
            throw new ApiException("todo not found.");
        }

        checkResource(todo, user);


        return todo;
    }


    public Todo addTodo(Todo todo, User user) {
        Todo todo1 = todoRepository.save(todo);
        todo1.setUser(user);

        todoRepository.save(todo1);

        return todo1;
    }

    public Todo updateTodo(Integer id, Todo todo, User user) {
        Todo t = todoRepository.findTodoById(id);

        if(t == null) {
            throw new ApiException("todo not found.");
        }

        checkResource(todo, user);

        t.setTitle(todo.getTitle());
        t.setActive(t.getActive());

        return t;
    }

    public Todo deleteTodo(Integer id, User user) {
        Todo todo = todoRepository.findTodoById(id);

        if(todo == null) {
            throw new ApiException("todo not found.");
        }

        checkResource(todo, user);

        todoRepository.deleteById(id);

        return todo;
    }

    public void checkResource(Todo todo, User user) {
        if(!Objects.equals(todo.getUser().getId(), user.getId())) {
            throw new ApiException("you don't have permissions to access this resource.");
        }
    }
}
