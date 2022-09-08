package com.example.SwDeveloperServer.domain.user.controller;

import com.example.SwDeveloperServer.domain.user.dto.request.PostJoinReq;
import com.example.SwDeveloperServer.domain.user.dto.request.PostLoginReq;
import com.example.SwDeveloperServer.domain.user.dto.response.PostFindEmailRes;
import com.example.SwDeveloperServer.domain.user.dto.response.PostFindPasswordRes;
import com.example.SwDeveloperServer.domain.user.dto.response.PostJoinRes;
import com.example.SwDeveloperServer.domain.user.dto.response.PostLoginRes;
import com.example.SwDeveloperServer.domain.user.service.UserServiceImpl;
import com.example.SwDeveloperServer.utils.jwt.JwtService;
import com.example.SwDeveloperServer.utils.response.BaseException;
import com.example.SwDeveloperServer.utils.response.ResponseService;
import com.example.SwDeveloperServer.utils.response.SuccessStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.example.SwDeveloperServer.utils.response.ErrorStatus.*;

@RestController
@Api(tags = "1. 유저 API ")
public class UserController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserServiceImpl userService;
    private final ResponseService responseService;
    private final JwtService jwtService;

    public UserController(UserServiceImpl userService, ResponseService responseService, JwtService jwtService) {
        this.userService = userService;
        this.responseService = responseService;
        this.jwtService = jwtService;
    }

    @ApiOperation(value = "회원가입")
    @ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = PostJoinRes.class)
    })
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody PostJoinReq postJoinReq) {
        try {
            // validation
            if (postJoinReq.getEmail() == null) {
                logger.error("이메일을 입력해 주세요.");
                throw new BaseException(POST_USERS_EMPTY_EMAIL);
            }
            if (postJoinReq.getPassword() == null) {
                logger.error("비밀번호을 입력해 주세요.");
                throw new BaseException(POST_USERS_EMPTY_PASSWORD);
            }
            if (postJoinReq.getName() == null) {
                logger.error("이름을 입력해 주세요.");
                throw new BaseException(POST_EMPTY_USER_NAME);
            }
            if (postJoinReq.getNickname() == null) {
                logger.error("사용자 이름을 입력해 주세요.");
                throw new BaseException(POST_USERS_EMPTY_NICKNAME);
            }
            if (postJoinReq.getPhone() == null) {
                logger.error("핸드폰 번호을 입력해 주세요.");
                throw new BaseException(POST_USERS_EMPTY_PHONE);
            }

            PostJoinRes postJoinRes = userService.join(postJoinReq);
            if (postJoinRes == null) logger.info("회원가입 실패");
            else logger.info("회원가입 성공");
            return responseService.successResult(postJoinRes, SuccessStatus.SUCCESS);

        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    @ApiOperation(value = "로그인")
    @ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = PostLoginRes.class)
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody PostLoginReq postLoginReq) throws BaseException {
        try {
            PostLoginRes postLoginRes = userService.login(postLoginReq);
            return responseService.successResult(postLoginRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    // 이메일 찾기
    @ApiOperation(value = "이메일 찾기")
    @ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = PostFindEmailRes.class)
    })
    @PostMapping("/find-email")
    public ResponseEntity<?> findEmail(@RequestBody Map<String, String> map) throws BaseException {
        try {
            if (map.get("phone").equals("")) {
                throw new BaseException(POST_USERS_EMPTY_PHONE);
            }
            PostFindEmailRes postFindEmailRes = userService.findEmail(map.get("phone"));
            return responseService.successResult(postFindEmailRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    // 비밀번호 찾기
    @ApiOperation(value = "비밀번호 찾기")
    @ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = PostFindPasswordRes.class)
    })
    @PostMapping("/find-password")
    public ResponseEntity<?> findPassword(@RequestBody Map<String, String> map) throws BaseException {
        try {
            if (map.get("email").equals("")) {
                throw new BaseException(POST_USERS_EMPTY_EMAIL);
            }
            PostFindPasswordRes postFindPasswordRes = userService.findPassword(map.get("email"));
            return responseService.successResult(postFindPasswordRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    // 이메일 인증

}
