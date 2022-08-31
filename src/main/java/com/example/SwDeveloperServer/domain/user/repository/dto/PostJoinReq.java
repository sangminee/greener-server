package com.example.SwDeveloperServer.domain.user.repository.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostJoinReq {

    private String email;
    private String password;
    private String name;
    private String nickname;
    private String userPhotoUrl;
    private String phone;

    private int userServiceAgreement;
    private int phoneAgreement;
    private int userType;
    private int state;
}
