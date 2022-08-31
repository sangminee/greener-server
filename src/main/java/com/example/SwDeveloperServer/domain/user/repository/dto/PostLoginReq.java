package com.example.SwDeveloperServer.domain.user.repository.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostLoginReq {
    private String email;
    private String password;
}
