package com.alice.todolist.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alice.todolist.database.entities.TodoEntity;;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long>{}
