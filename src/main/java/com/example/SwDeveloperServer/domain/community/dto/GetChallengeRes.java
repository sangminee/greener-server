package com.example.SwDeveloperServer.domain.community.dto;

import com.example.SwDeveloperServer.domain.community.entity.ChallengePost;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Queue;

@Getter
@Setter
public class GetChallengeRes {

    private Long userId;
    private String email;
    private String name;


    private String challengeTitle;
    private LocalDateTime challengeCreatedAt;
    private String challengeTitlePhoto;
    private String challengePostContent;

    private LocalDateTime toStartDate;
    private LocalDateTime toEndDate;
//    private LocalDateTime goalAlarmTime;
}
