package com.example.SwDeveloperServer.domain.toDoList.repository;

import com.example.SwDeveloperServer.domain.toDoList.entity.Todo;
import com.example.SwDeveloperServer.domain.toDoList.entity.TodoPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoPhotoRepository extends JpaRepository<TodoPhoto, Long> {
}
