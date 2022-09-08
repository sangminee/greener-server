package com.example.SwDeveloperServer.domain.home.controller;

import com.example.SwDeveloperServer.domain.home.dto.*;
import com.example.SwDeveloperServer.domain.home.service.HomeServiceImpl;
import com.example.SwDeveloperServer.utils.jwt.JwtService;
import com.example.SwDeveloperServer.utils.response.BaseException;
import com.example.SwDeveloperServer.utils.response.ResponseService;
import com.example.SwDeveloperServer.utils.response.SuccessStatus;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags ="2. 홈 API ")
public class HomeController {

    private final HomeServiceImpl homeService;
    private final ResponseService responseService;
    private final JwtService jwtService;

    public HomeController(HomeServiceImpl homeService, ResponseService responseService, JwtService jwtService) {
        this.homeService = homeService;
        this.responseService = responseService;
        this.jwtService = jwtService;
    }

    /**
     * 메인페이지 API
     */
    @ApiOperation(value = "메인페이지")
    @ApiResponses({  // Response Message 에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = GetHomeRes.class)
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @GetMapping("/")
    public ResponseEntity<?> getHome() throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            GetHomeRes getHomeRes = homeService.getHome(userId);
            return responseService.successResult(getHomeRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

}
