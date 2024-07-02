package com.alice.todolist.database.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length=40)
    private String name;

    @Column(length=255)
    private String description;
    
    @Column()
    private boolean isCompleted;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    public TodoEntity(String name, String description) {
        this.name = name;
        this.description = description;
        this.isCompleted = false;
    }

    public TodoEntity() {}
}
