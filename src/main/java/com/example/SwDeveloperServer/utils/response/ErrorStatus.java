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

    // 유저
    POST_USERS_EMPTY_EMAIL(false, 2010, "이메일을 입력해주세요."),
    POST_EMPTY_USER_NAME(false, 2012, "이름을 입력해주세요."),
    POST_USERS_EMPTY_NICKNAME(false, 2013, "사용자 이름을 입력해주세요."),
    POST_USERS_EMPTY_PASSWORD(false, 2014, "비밀번호를 입력해주세요."),
    POST_USERS_EMPTY_PHONE(false, 2015, "핻드폰 번호를 입력해주세요."),
    POST_USERS_EMPTY_PHOTO_URL(false, 2016, "사진 URL를 제대로를 입력해주세요."),

    // 챌린지
    POST_EMPTY_CHALLENGE_POST_TITLE(false,2020,"챌린지 제목을 입력해주세요."),
    POST_EMPTY_CHALLENGE_POST_PHOTO(false,2021,"챌린지 제목을 입력해주세요."),
    POST_EMPTY_CHALLENGE_POST_SUCCESS_POINT(false,2022,"획득 포인트를 입력해주세요."),
    POST_EMPTY_CHALLENGE_POST_LIMIT_PEOPLE(false,2023,"인원 제한를 입력해주세요."),
    POST_EMPTY_CHALLENGE_POST_TO_START_DATE(false,2024,"챌린지 시작날짜를 입력해주세요."),
    POST_CHECK_CHALLENGE_POST_TO_START_DATE(false,2024,"챌린지 시작날짜를 형식에 맞게 입력해주세요."),
    POST_EMPTY_CHALLENGE_POST_TO_END_DATE(false,2025,"챌린지 종료 날짜를 입력해주세요."),
    POST_CHECK_CHALLENGE_POST_TO_END_DATE(false,2025,"챌린지 종료 날짜를 형식에 맞게 입력해주세요."),
    POST_EMPTY_CHALLENGE_POST_GET_HOW_TO(false,2026,"참여방법을 입력해주세요."),


    INVALID_CHALLENGE_POST(false,2040,"존재하지 않은 챌린지입니다."),
    INVALID_CHALLENGE_POST_COMMENT(false,2041,"존재하지 않은 챌린지 후기입니다."),
    INVALID_CHALLENGE_POST_COMMENT_USER(false,2042,"삭제 권한이 없는 유저입니다."),

    EXIST_CHALLENGE_POST_JOIN_USER(false,2043,"이미 해당 챌린지에 참여 중인 유저입니다."),

    EXIST_INFO_POST_SCRAP(false,2044,"이미 스크랩되었습니다."),
    INVALID_INFO_POST_SCRAP_USER(false,2045,"스크랩을 취소할 권한이 없습니다."),

    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;
}