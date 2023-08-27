package com.example.week06d1security.Controller;


import com.example.week06d1security.Api.ApiResponseWithResponse;
import com.example.week06d1security.Model.Todo;
import com.example.week06d1security.Model.User;
import com.example.week06d1security.Service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/get")
    public ResponseEntity<List<Todo>> findAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(todoService.findAll(user));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Todo> findById(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(todoService.findById(id, user));
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponseWithResponse<Todo>> add(@RequestBody @Valid Todo todo, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok((new ApiResponseWithResponse<>("the todo have been added", todoService.addTodo(todo, user))));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseWithResponse<Todo>> edit(@PathVariable Integer id, @RequestBody @Valid Todo todo, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok((new ApiResponseWithResponse<>("the todo have been updated", todoService.updateTodo(id, todo, user))));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseWithResponse<Todo>> delete(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok((new ApiResponseWithResponse<>("the todo have been updated", todoService.deleteTodo(id, user))));
    }

}
