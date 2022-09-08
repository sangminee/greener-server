package com.example.SwDeveloperServer.domain.myPage.controller;

import com.example.SwDeveloperServer.domain.myPage.dto.requset.ChangeNameReq;
import com.example.SwDeveloperServer.domain.myPage.dto.requset.ChangeNicknameReq;
import com.example.SwDeveloperServer.domain.myPage.dto.requset.ChangeUserPhotoReq;
import com.example.SwDeveloperServer.domain.myPage.dto.response.ChangeRes;
import com.example.SwDeveloperServer.domain.myPage.dto.response.GetMyPage;
import com.example.SwDeveloperServer.domain.myPage.entity.Stamp;
import com.example.SwDeveloperServer.domain.myPage.service.MyPageServiceImpl;
import com.example.SwDeveloperServer.utils.jwt.JwtService;
import com.example.SwDeveloperServer.utils.response.BaseException;
import com.example.SwDeveloperServer.utils.response.SuccessStatus;
import com.example.SwDeveloperServer.utils.response.ResponseService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.SwDeveloperServer.utils.response.ErrorStatus.*;

@RestController
@Api(tags = "3. 마이페이지 API ")
public class MyPageController {

    private final MyPageServiceImpl myPageService;
    private final ResponseService responseService;
    private final JwtService jwtService;

    public MyPageController(MyPageServiceImpl myPageService, ResponseService responseService, JwtService jwtService) {
        this.myPageService = myPageService;
        this.responseService = responseService;
        this.jwtService = jwtService;
    }

    /**
     * 회원 별 마이페이지 보기  API
     */
    @ApiOperation(value = "회원 별 마이페이지 보기 ")
    @ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = GetMyPage.class)
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @GetMapping("/myPage")
    public ResponseEntity<?> getMyPage() throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            GetMyPage getMyPage = myPageService.getMyPage(userId);
            return responseService.successResult(getMyPage, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    /**
     * 앱 알람 on / off -
     */

    /**
     * 회원이 참여하는 챌린지 목록 보기
     */

    /**
     * 회원이 스크랩한 정보 목록 보기
     */


    /**
     * 회원 별 스탬프 보기
     */
    @ApiOperation(value = "회원 별 스탬프 보기")
    @ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = Stamp.class)
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @GetMapping("/stamp")
    public ResponseEntity<?> getStamps() throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            List<Stamp> stamps = myPageService.getStamps(userId);
            return responseService.successResult(stamps, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    /**
     * 스탬프 정보 보기
     */
    @ApiOperation(value = "스탬프 정보 보기")
    @ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = Stamp.class)
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @GetMapping("/stamp/{stampId}")
    public ResponseEntity<?> getStamp(@PathVariable String stampId) throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            Stamp stamp = myPageService.getStamp(userId, stampId);
            return responseService.successResult(stamp, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }


    /**
     * 닉네임 변경
     */
    @ApiOperation(value = "닉네임 변경")
    @ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = ChangeRes.class)
    })
    @PostMapping("/change/nickname")
    public ResponseEntity<?> changeNickname(@RequestBody ChangeNicknameReq changeNicknameReq) throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            if(changeNicknameReq.getNickname().equals("")){
                throw new BaseException(POST_USERS_EMPTY_NICKNAME);
            }
            ChangeRes changeRes = myPageService.changeNickname(userId,changeNicknameReq);
            return responseService.successResult(changeRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }


    /**
     * 성명 변경
     */
    @ApiOperation(value = "성명 변경")
    @ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = ChangeRes.class)
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @PostMapping("/change/name")
    public ResponseEntity<?> changeName(@RequestBody ChangeNameReq changeNameReq) throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            if(changeNameReq.getName().equals("")){
                throw new BaseException(POST_EMPTY_USER_NAME);
            }
            ChangeRes changeRes = myPageService.changeName(userId, changeNameReq);
            return responseService.successResult(changeRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    /**
     * 사용자 사진 변경
     */
    @ApiOperation(value = "프로필 사진 변경")
    @ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = ChangeRes.class)
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @PostMapping("/change/userPhoto")
    public ResponseEntity<?> changeUserPhoto(@RequestBody ChangeUserPhotoReq changeUserPhotoReq) throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            if(changeUserPhotoReq.getUserPhotoUrl().equals("")){
                throw new BaseException(POST_USERS_EMPTY_PHOTO_URL);
            }
            ChangeRes changeRes = myPageService.changeUserPhoto(userId, changeUserPhotoReq);
            return responseService.successResult(changeRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    /**
     * 생일 변경
     */

}
