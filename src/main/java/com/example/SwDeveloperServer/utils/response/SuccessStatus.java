package com.example.SwDeveloperServer.utils.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessStatus {

    SUCCESS(true, 1000, "요청에 성공하였습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;
}
