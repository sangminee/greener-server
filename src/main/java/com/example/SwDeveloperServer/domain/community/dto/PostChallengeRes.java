package com.example.SwDeveloperServer.domain.community.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostChallengeRes {
    private String message;

    public PostChallengeRes(String s) {
        this.message = s;
    }
}
