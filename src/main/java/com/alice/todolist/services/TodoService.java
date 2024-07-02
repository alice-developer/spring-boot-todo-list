package com.alice.todolist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alice.todolist.database.entities.TodoEntity;
import com.alice.todolist.database.repositories.TodoRepository;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public TodoEntity createTodo(TodoEntity entity) {
        if (
            entity.getName().equals(null) || entity.getDescription().equals(null) ||
            entity.getName().length() > 40 || entity.getDescription().length() > 255
        ) return null;

        return todoRepository.save(entity);
    }

    public List<TodoEntity> findAll() {
        return todoRepository.findAll();
    }

    public TodoEntity findById(Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    public boolean deleteById(Long id) {
        TodoEntity todo = findById(id);

        if (todo == null)
            return false;

        try {
            todoRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException err) {
            return false;
        }
    }

    public TodoEntity updateCompletedState(Long id) {
        TodoEntity todo = findById(id);

        if (todo != null) {
            todo.setCompleted(!todo.isCompleted());
            todoRepository.save(todo);

            return todo;
        }

        return null;
    }
}
