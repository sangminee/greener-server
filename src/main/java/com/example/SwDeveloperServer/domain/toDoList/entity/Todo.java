package com.example.SwDeveloperServer.domain.toDoList.entity;

import com.example.SwDeveloperServer.domain.user.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todoId;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    private String goalDescription;
    private LocalDateTime toStartDate;
    private LocalDateTime toEndDate;

    private Timestamp goalAlarmTime;

//    // 해당 값이 -1이 되면
//    private int listGoalRepetition;

    @ApiModelProperty(notes = "인증 상태 - 0 : 인증 o,  1 : 인증 x", example="1")
    @Enumerated(EnumType.STRING)
    private ToDoState state;

    @Builder
    public Todo(User user, String goalDescription,
                LocalDateTime toStartDate, LocalDateTime toEndDate){
        this.user = user;
        this.goalDescription = goalDescription;
        this.toStartDate = toStartDate;
        this.toEndDate = toEndDate;
        this.state = ToDoState.Non_certification;
    }

    public static Todo toEntity(User user, String value, LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime){
        return Todo.builder()
                .user(user)
                .goalDescription(value)
                .toStartDate(startLocalDateTime)
                .toEndDate(endLocalDateTime)
                .build();
    }

}
