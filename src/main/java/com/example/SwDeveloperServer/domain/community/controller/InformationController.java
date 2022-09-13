package com.example.SwDeveloperServer.domain.community.controller;

import com.example.SwDeveloperServer.utils.jwt.JwtService;
import com.example.SwDeveloperServer.utils.response.ResponseService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags =" 7. 커뮤니티 - 정보 API ")
public class InformationController {

    private final ResponseService responseService;
    private final JwtService jwtService;

    public InformationController(ResponseService responseService, JwtService jwtService) {
        this.responseService = responseService;
        this.jwtService = jwtService;
    }

    /**
     * 정보 등록 API
     */

    /**
     * 정보 리스트 보기 API
     */

    /**
     * 정보 보기 API
     */


    /**
     * 정보 스크랩 하기 API
     */

}
