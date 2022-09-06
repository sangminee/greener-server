package com.example.SwDeveloperServer.utils.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorStatus {

    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),

    INVALID_USER_EMAIL(false,2005,"없는 이메일입니다. 다시입력해 주세요."),
    INVALID_USER_PHONE(false,2006,"없는 핸드폰번호입니다. 다시입력해 주세요."),
    INVALID_USER_PASSWORD(false,2007,"비밀번호를 찾을 수 없습니다."),

    POST_USERS_EMPTY_EMAIL(false, 2010, "이메일을 입력해주세요."),
    POST_EMPTY_USER_NAME(false, 2012, "이름을 입력해주세요."),
    POST_USERS_EMPTY_NICKNAME(false, 2013, "사용자 이름을 입력해주세요."),
    POST_USERS_EMPTY_PASSWORD(false, 2014, "비밀번호를 입력해주세요."),
    POST_USERS_EMPTY_PHONE(false, 2015, "핻드폰 번호를 입력해주세요."),
    POST_USERS_EMPTY_PHOTO_URL(false, 2016, "사진 URL를 제대로를 입력해주세요."),

    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;
}