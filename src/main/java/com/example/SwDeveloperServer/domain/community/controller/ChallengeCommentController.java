package com.example.SwDeveloperServer.domain.community.controller;

import com.example.SwDeveloperServer.domain.community.dto.req.PostChallengeReq;
import com.example.SwDeveloperServer.domain.community.dto.req.PostCommentReq;
import com.example.SwDeveloperServer.domain.community.dto.res.DeleteResultRes;
import com.example.SwDeveloperServer.domain.community.dto.res.GetChallengeRes;
import com.example.SwDeveloperServer.domain.community.dto.res.GetCommentRes;
import com.example.SwDeveloperServer.domain.community.dto.res.PostResultRes;
import com.example.SwDeveloperServer.domain.community.service.ChallengePostServiceImpl;
import com.example.SwDeveloperServer.utils.jwt.JwtService;
import com.example.SwDeveloperServer.utils.response.BaseException;
import com.example.SwDeveloperServer.utils.response.ResponseService;
import com.example.SwDeveloperServer.utils.response.SuccessStatus;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags ="7. 커뮤니티 - 챌린지 후기 API ")
public class ChallengeCommentController {

    private final ResponseService responseService;
    private final ChallengePostServiceImpl challengePostService;
    private final JwtService jwtService;

    public ChallengeCommentController(ResponseService responseService, ChallengePostServiceImpl challengePostService, JwtService jwtService) {
        this.responseService = responseService;
        this.challengePostService = challengePostService;
        this.jwtService = jwtService;
    }

    /**
     * 댓글(후기) 달기 API
     */
    @ApiOperation(value = "챌린지 후기 달기")
    @ApiResponses({  // Response Message 에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = GetChallengeRes.class)
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @PostMapping("/challenge/{challengePostId}/comment")
    public ResponseEntity<?> setChallengeComment(@PathVariable Long challengePostId,
                                                 @RequestBody PostCommentReq postCommentReq) throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            GetChallengeRes getChallengeRes
                    = challengePostService.setChallengeComment(userId,challengePostId,postCommentReq);
            return responseService.successResult(getChallengeRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    /**
     * 댓글(후기) 보기 API - 전체
     */
    @ApiOperation(value = "댓글(후기) 보기 - 전체")
    @ApiResponses({  // Response Message 에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = GetCommentRes.class),
            @ApiResponse(code = 2041, message = "존재하지 않은 챌린지 후기입니다.")
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @GetMapping("/challenge/{challengePostId}/comment")
    public ResponseEntity<?> getChallengeComments(@PathVariable Long challengePostId) throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            List<GetCommentRes> getCommentRes = challengePostService.getChallengeComments(userId,challengePostId);
            return responseService.successResult(getCommentRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    /**
     * 댓글(후기) 보기 API - 하나
     */
    @ApiOperation(value = "댓글(후기) 보기 - 하나")
    @ApiResponses({  // Response Message 에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = GetCommentRes.class),
            @ApiResponse(code = 2041, message = "존재하지 않은 챌린지 후기입니다.")
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @GetMapping("/challenge/comment/{challengePostCommentId}")
    public ResponseEntity<?> getChallengeComment(@PathVariable Long challengePostCommentId) throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            GetCommentRes getCommentRes = challengePostService.getChallengeComment(userId,challengePostCommentId);
            return responseService.successResult(getCommentRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    /**
     * 댓글 삭제 하기 API
     */
    @ApiOperation(value = "댓글 삭제 하기")
    @ApiResponses({  // Response Message 에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = DeleteResultRes.class),
            @ApiResponse(code = 2041, message = "존재하지 않은 챌린지 후기입니다."),
            @ApiResponse(code = 2042, message = "삭제 권한이 없는 유저입니다.")
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @DeleteMapping("/challenge/comment/{challengePostCommentId}")
    public ResponseEntity<?> deleteChallengeComment(@PathVariable Long challengePostCommentId) throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            DeleteResultRes deleteResultRes = challengePostService.deleteChallengeComment(userId, challengePostCommentId);
            return responseService.successResult(deleteResultRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }
}
