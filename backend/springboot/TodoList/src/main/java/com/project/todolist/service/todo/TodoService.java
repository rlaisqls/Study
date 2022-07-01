package com.project.todolist.service.todo;

import com.project.todolist.dto.request.CreateTodoRequest;
import com.project.todolist.dto.request.ModifyTodoRequest;
import com.project.todolist.dto.response.FindTodoListResponse;
import com.project.todolist.dto.response.FindTodoInfoResponse;
import com.project.todolist.dto.response.FindTodoResponse;
import com.project.todolist.entity.todo.Todo;
import com.project.todolist.entity.todo.TodoRepository;
import com.project.todolist.entity.user.User;
import com.project.todolist.entity.user.UserRepository;
import com.project.todolist.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    private final UserRepository userRepository;

    private final UserFacade userFacade;

    public void createTodo(CreateTodoRequest request) {

        User user = userFacade.currentUser();

        Todo todo = Todo
                .builder()
                .writer(user)
                .title(request.getTitle())
                .createdDate(LocalDateTime.now())
                .content(request.getContent())
                .isChecked(false)
                .build();

        todoRepository.save(todo);
    }

    public void modifyTodo(String todoId, ModifyTodoRequest request) {
    }

    public void deleteTodo(String todoId) {
    }

    public List<FindTodoResponse> findAllTodo() {
    }

    public List<FindTodoResponse> findUserTodo(String userId) {
    }

    public FindTodoInfoResponse findMyTodo(String todoId) {
    }

    public FindTodoInfoResponse findTodo(String todoId) {
    }
}