package com.example.SwDeveloperServer.domain.user.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@ApiModel(description = "회원가입")
@Setter
public class PostJoinReq {

    @ApiModelProperty(notes = "이메일", example="test000")
    private String email;
    @ApiModelProperty(notes = "비밀번호", example="test000")
    private String password;
    @ApiModelProperty(notes = "이름", example="test000")
    private String name;
    @ApiModelProperty(notes = "닉네임", example="test000")
    private String nickname;
    @ApiModelProperty(notes = "프로필사진 url", example="test000")
    private String userPhotoUrl;
    @ApiModelProperty(notes = "핸드폰번호", example="test000")
    private String phone;

    @ApiModelProperty(notes = "서비스 약관 동의 유무 - 0 : 동의 o,  1 : 동의 x", example="0")
    private int userServiceAgreement;
    @ApiModelProperty(notes = "휴대폰 인증 유무 - 0 : 인증 o,  1 : 인증 x", example="0")
    private int phoneAgreement;
    @ApiModelProperty(notes = "유저 타입 - 0 : 회원, 1 : 관리자", example="0")
    private int userType;
    @ApiModelProperty(notes = "유저 상태 - 0:활성, 1:비활성", example="0")
    private int state;
}
