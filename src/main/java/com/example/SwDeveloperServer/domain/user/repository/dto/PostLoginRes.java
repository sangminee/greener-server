package com.example.SwDeveloperServer.domain.user.repository.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostLoginRes {
    private String message;

    public PostLoginRes(String message) {
        this.message = message;
    }
}
