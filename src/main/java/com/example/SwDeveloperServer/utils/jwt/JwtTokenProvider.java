package com.example.SwDeveloperServer.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    // 토큰 유효시간 30분
    private long tokenValidTime = 30*60*1000L;
    // 1개월
    private long REFRESH_TOKEN_VALIDATiON_SECOND = 60 * 60 * 24 * 30;

    // jwt 토큰 생성
    public String createToken(Long userId){
        Claims claims = Jwts.claims(); // JWT payload에 저장되는 정보단위
        claims.put("userId", userId);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // 만료시간 설정
                .signWith(SignatureAlgorithm.HS256, Secret.JWT_SECRET_KEY)
                // 사용할 암호화 알고리즘과 signature에 들어갈 secret 값 세팅
                .compact();
    }

//    // jwt refresh 토큰 생성
//    public String createRefreshToken(){
//
//    }

//    // JWT 토큰에서 인증정보 조회
//    public Authentication getAuthentication(String token){
//
//    }
}
