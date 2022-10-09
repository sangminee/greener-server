package com.example.SwDeveloperServer.domain.user.entity;

import com.example.SwDeveloperServer.domain.user.enums.UserJoinType;
import com.example.SwDeveloperServer.domain.user.enums.UserState;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String email;
    private String password;
    private String name;
    private String nickname;
    private String userPhotoUrl;
    private String phone;

    private LocalDateTime userCreateTime;

    private int userServiceAgreement;
    private int phoneAgreement;

    @Enumerated(EnumType.STRING)
    private UserJoinType userType;

    @Enumerated(EnumType.STRING)
    private UserState state;

    @OneToOne
    @JoinColumn(name = "plant_Id")
    private PlantPhoto plantPhoto;

    @Builder
    public User(String email, String password, String name, String nickname,
                String userPhotoUrl, String phone,
                int userServiceAgreement, int phoneAgreement, String userType){

        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.userPhotoUrl = userPhotoUrl;
        this.phone = phone;
        this.userCreateTime = LocalDateTime.now();

        this.userServiceAgreement = userServiceAgreement;
        this.phoneAgreement = phoneAgreement;
        this.userType = UserJoinType.valueOf(userType);
        this.state = UserState.ACTIVE;
    }
}
