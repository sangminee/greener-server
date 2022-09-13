package com.example.SwDeveloperServer.domain.community.entity;

import com.example.SwDeveloperServer.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long challengePostId;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    private String challengeTitle;

    private LocalDateTime challengeCreatedAt;
    private String challengeTitlePhoto;
    private String challengePostContent;

    private LocalDateTime toStartDate;
    private LocalDateTime toEndDate;
    private LocalDateTime goalAlarmTime;

    private int state;


}
