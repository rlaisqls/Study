package com.project.todolist.controller;

import com.project.todolist.dto.request.CreateTodoRequest;
import com.project.todolist.dto.request.ModifyTodoRequest;
import com.project.todolist.dto.response.FindTodoListResponse;
import com.project.todolist.dto.response.FindTodoInfoResponse;
import com.project.todolist.dto.response.FindTodoResponse;
import com.project.todolist.service.todo.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todo")
    public void createTodo(@RequestBody @Valid CreateTodoRequest request){
        todoService.createTodo(request);
    }

    @PostMapping("/todo/{todoId}")
    public void modifyTodo(@PathVariable String todoId, @RequestBody @Valid ModifyTodoRequest request){
        todoService.modifyTodo(todoId, request);
    }

    @DeleteMapping("/todo/{todoId}")
    public void deleteTodo(@PathVariable String todoId){
        todoService.deleteTodo(todoId);
    }

    @GetMapping("/todo/list/all")
    public List<FindTodoResponse> findAllTodo() {
        return todoService.findAllTodo();
    }

    @GetMapping("/todo/list/{userId}")
    public List<FindTodoResponse> findUserTodo(@PathVariable String userId) {
        return todoService.findUserTodo(userId);
    }

    @GetMapping("/todo/{todoId}")
    public FindTodoInfoResponse findMyTodo(@PathVariable String todoId) {
        return todoService.findMyTodo(todoId);
    }

    @GetMapping("/todo/{todoId}")
    public FindTodoInfoResponse findTodo(@PathVariable String todoId) {
        return todoService.findTodo(todoId);
    }
}