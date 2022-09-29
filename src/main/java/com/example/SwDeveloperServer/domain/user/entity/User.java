package com.example.SwDeveloperServer.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private int userType;
    private int state;

    private String refreshToken;

    @OneToOne
    @JoinColumn(name = "plantId")
    private PlantPhoto plantPhoto;
}
