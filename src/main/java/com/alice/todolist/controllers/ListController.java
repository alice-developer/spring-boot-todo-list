package com.alice.todolist.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alice.todolist.database.entities.TodoEntity;
import com.alice.todolist.services.TodoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

class ListCreateBody {
    public String name;
    public String description;
}

@RestController()
@RequestMapping("/api")
public class ListController {
    @Autowired
    TodoService todoService;

    @GetMapping("/list")
    public ResponseEntity<?> getToDos() {
        return new ResponseEntity<List<TodoEntity>>(todoService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createToDo(
        @RequestBody ListCreateBody data
    ) {
        TodoEntity todo = todoService.createTodo(new TodoEntity(data.name, data.description));

        if (todo == null)
            return new ResponseEntity<String>("Nome ou descrição são inválidos.", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<TodoEntity>(todo, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<?> removeTodo(@RequestParam Long id) {
        boolean result = todoService.deleteById(id);

        if (!result)
            return new ResponseEntity<String>("Nenhuma tarefa encontrada com esse ID.", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<String>("Tarefa removida com sucesso!", HttpStatus.OK);
    }

    @PatchMapping("/complete")
    public ResponseEntity<?> completeTodo(@RequestParam Long id) {
        TodoEntity updateResult = todoService.updateCompletedState(id);

        if (updateResult == null)
            return new ResponseEntity<String>("Não foi possível atualizar o ToDo de ID passado.", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<TodoEntity>(updateResult, HttpStatus.OK);
    }
}
