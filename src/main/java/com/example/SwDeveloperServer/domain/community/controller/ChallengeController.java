package com.example.SwDeveloperServer.domain.community.controller;

import com.example.SwDeveloperServer.domain.community.dto.GetChallengeRes;
import com.example.SwDeveloperServer.domain.community.dto.PostChallengeReq;
import com.example.SwDeveloperServer.domain.community.dto.PostChallengeRes;
import com.example.SwDeveloperServer.domain.community.service.ChallengePostServiceImpl;
import com.example.SwDeveloperServer.domain.home.dto.GetHomeRes;
import com.example.SwDeveloperServer.utils.jwt.JwtService;
import com.example.SwDeveloperServer.utils.response.BaseException;
import com.example.SwDeveloperServer.utils.response.ResponseService;
import com.example.SwDeveloperServer.utils.response.SuccessStatus;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags ="6. 커뮤니티 - 챌린지 API ")
public class ChallengeController {

    private final ResponseService responseService;
    private final ChallengePostServiceImpl challengePostService;
    private final JwtService jwtService;

    public ChallengeController(ResponseService responseService, ChallengePostServiceImpl challengePostService, JwtService jwtService) {
        this.responseService = responseService;
        this.challengePostService = challengePostService;
        this.jwtService = jwtService;
    }

    /**
     * 챌린지 등록 API
     */
    @ApiOperation(value = "챌린지 등록")
    @ApiResponses({  // Response Message 에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = PostChallengeRes.class)
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @PostMapping("/challenge")
    public ResponseEntity<?> setChallengePost(@RequestBody PostChallengeReq postChallengeReq) throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            PostChallengeRes postChallengeRes = challengePostService.setChallengePost(userId, postChallengeReq);
            return responseService.successResult(postChallengeRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    /**
     * 챌린지 리스트 보기 API
     */
    @ApiOperation(value = "챌린지 리스트 보기")
    @ApiResponses({  // Response Message 에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = GetChallengeRes.class)
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @GetMapping("/challenge")
    public ResponseEntity<?> getChallengePosts() throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            List<GetChallengeRes> getChallengeResList = challengePostService.getChallengePosts(userId);
            return responseService.successResult(getChallengeResList, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    /**
     * 챌린지 보기 API
     */
    @ApiOperation(value = "챌린지 보기")
    @ApiResponses({  // Response Message 에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = GetChallengeRes.class)
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @GetMapping("/challenge/{challengePostId}")
    public ResponseEntity<?> getChallengePost(@PathVariable Long challengePostId) throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            GetChallengeRes getChallengeRes = challengePostService.getChallengePost(userId,challengePostId);
            return responseService.successResult(getChallengeRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    /**
     * 댓글 달기 API
     */

    /**
     * 댓글 삭제 하기 API
     */


}
