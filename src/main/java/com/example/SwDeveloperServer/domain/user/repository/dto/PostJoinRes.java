package com.example.SwDeveloperServer.domain.user.repository.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostJoinRes {
    private long userId;
    private String message;

    public PostJoinRes(long userId, String message) {
        this.userId = userId;
        this.message = message;
    }
}
