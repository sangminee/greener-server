package com.example.SwDeveloperServer.domain.toDoList.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long toDoPhotoId;

    @ManyToOne
    @JoinColumn(name="todoId")
    private Todo todo;

    private String toDoListPhotoUrl;

    private LocalDateTime createdAt;

    @Builder
    public TodoPhoto(Todo todo, String toDoListPhotoUrl) {
        this.todo = todo;
        this.toDoListPhotoUrl = toDoListPhotoUrl;
       this.createdAt = LocalDateTime.now();
    }

    public static TodoPhoto toEntity(Todo todo, String toDoListPhotoUrl) {
        return TodoPhoto.builder()
                .todo(todo)
                .toDoListPhotoUrl(toDoListPhotoUrl)
//                .createdAt()
                .build();
    }

}
