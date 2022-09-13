package com.example.SwDeveloperServer.domain.community.controller;

import com.example.SwDeveloperServer.utils.jwt.JwtService;
import com.example.SwDeveloperServer.utils.response.ResponseService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags ="6. 커뮤니티 - 챌린지 API ")
public class ChallengeController {

    private final ResponseService responseService;
    private final JwtService jwtService;

    public ChallengeController(ResponseService responseService, JwtService jwtService) {
        this.responseService = responseService;
        this.jwtService = jwtService;
    }

    /**
     * 챌린지 등록 API
     */

    /**
     * 챌린지 리스트 보기 API
     */

    /**
     * 챌린지 보기 API
     */

    /**
     * 댓글 달기 API
     */

    /**
     * 댓글 삭제 하기 API
     */


}
