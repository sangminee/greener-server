package com.example.SwDeveloperServer.domain.community.controller;

import com.example.SwDeveloperServer.domain.community.dto.req.PostInfoReq;
import com.example.SwDeveloperServer.domain.community.dto.res.DeleteResultRes;
import com.example.SwDeveloperServer.domain.community.dto.res.GetInformRes;
import com.example.SwDeveloperServer.domain.community.dto.res.PostResultRes;
import com.example.SwDeveloperServer.domain.community.service.InformationPostServiceImpl;
import com.example.SwDeveloperServer.utils.jwt.JwtService;
import com.example.SwDeveloperServer.utils.response.BaseException;
import com.example.SwDeveloperServer.utils.response.ResponseService;
import com.example.SwDeveloperServer.utils.response.SuccessStatus;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags ="8. 커뮤니티 - 정보 API ")
public class InformationController {

    private final ResponseService responseService;
    private final InformationPostServiceImpl informationPostService;
    private final JwtService jwtService;

    public InformationController(ResponseService responseService, InformationPostServiceImpl informationPostService, JwtService jwtService) {
        this.responseService = responseService;
        this.informationPostService = informationPostService;
        this.jwtService = jwtService;
    }

    /**
     * 정보 등록 API
     */
    @ApiOperation(value = "정보 등록")
    @ApiResponses({  // Response Message 에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = PostResultRes.class)
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @PostMapping("/Information")
    public ResponseEntity<?> setInformationPost(@RequestBody PostInfoReq postInfoReq) throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            PostResultRes postResultRes = informationPostService.setInformationPost(userId, postInfoReq);
            return responseService.successResult(postResultRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    /**
     * 정보 리스트 보기 API
     */
    @ApiOperation(value = "정보 리스트 보기")
    @ApiResponses({  // Response Message 에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = GetInformRes.class)
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @GetMapping("/Information")
    public ResponseEntity<?> getInformationPosts() throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            List<GetInformRes> getInformResList = informationPostService.getInformationPosts(userId);
            return responseService.successResult(getInformResList, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    /**
     * 정보 보기 API
     */
    @ApiOperation(value = "정보 리스트 보기")
    @ApiResponses({  // Response Message 에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = GetInformRes.class)
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @GetMapping("/Information/{informationPostId}")
    public ResponseEntity<?> getInformationPost(@PathVariable Long informationPostId) throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            GetInformRes getInformRes = informationPostService.getInformationPost(userId,informationPostId);
            return responseService.successResult(getInformRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    /**
     * 정보 스크랩 하기 API
     */
    @ApiOperation(value = "정보 스크랩")
    @ApiResponses({  // Response Message 에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = PostResultRes.class),
            @ApiResponse(code = 2044, message = "이미 스크랩되었습니다.")
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @PostMapping("/Information/{informationPostId}")
    public ResponseEntity<?> setInformationPostScrap(@PathVariable Long informationPostId) throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            PostResultRes postResultRes = informationPostService.setInformationPostScrap(userId,informationPostId);
            return responseService.successResult(postResultRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    /**
     * 정보 스크랩 취소하기 API
     */
    @ApiOperation(value = "정보 스크랩 취소하기")
    @ApiResponses({  // Response Message 에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = DeleteResultRes.class),
            @ApiResponse(code = 2045, message = "스크랩을 취소할 권한이 없습니다.")
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @DeleteMapping("/Information/scrap/{informationPostScrapId}")
    public ResponseEntity<?> deleteInformationPostScrap(@PathVariable Long informationPostScrapId) throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            DeleteResultRes deleteResultRes = informationPostService.deleteInformationPostScrap(userId,informationPostScrapId);
            return responseService.successResult(deleteResultRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

}
