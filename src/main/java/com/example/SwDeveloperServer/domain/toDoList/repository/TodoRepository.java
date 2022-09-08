package com.example.SwDeveloperServer.domain.toDoList.repository;

import com.example.SwDeveloperServer.domain.toDoList.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
