package com.example.SwDeveloperServer.domain.toDoList.entity;

import com.example.SwDeveloperServer.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long toDoPhotoId;

    @ManyToOne
    @JoinColumn(name="todoId")
    private Todo todo;

    private String toDoListPhotoUrl;
    private Timestamp createdAt;
}
