package com.example.SwDeveloperServer.domain.user.dto.request;

import com.example.SwDeveloperServer.domain.user.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@ApiModel(description = "회원가입")
@Setter
public class PostJoinReq {

    @NotBlank(message = "email is not null")
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

    @ApiModelProperty(notes = "유저 타입 - USER, ADMIN", example="USER")
    private String userType;

    public PostJoinReq(String email, String password, String name,
                       String nickname, String userPhotoUrl, String phone,
                       int userServiceAgreement, int phoneAgreement,
                       String userType) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.userPhotoUrl = userPhotoUrl;
        this.phone = phone;
        this.userServiceAgreement = userServiceAgreement;
        this.phoneAgreement = phoneAgreement;
        this.userType = userType;
    }

    public User toEntity(){
        return User.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .nickname(this.nickname)
                .userPhotoUrl(this.userPhotoUrl)
                .phone(this.phone)
                .userServiceAgreement(this.userServiceAgreement)
                .phoneAgreement(this.phoneAgreement)
                .userType(this.userType)
                .build();
    }
}
