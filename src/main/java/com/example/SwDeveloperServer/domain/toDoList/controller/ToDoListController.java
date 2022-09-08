package com.example.SwDeveloperServer.domain.toDoList.controller;

import com.example.SwDeveloperServer.domain.myPage.dto.response.GetMyPage;
import com.example.SwDeveloperServer.domain.toDoList.service.ToDoListServiceImpl;
import com.example.SwDeveloperServer.utils.jwt.JwtService;
import com.example.SwDeveloperServer.utils.response.BaseException;
import com.example.SwDeveloperServer.utils.response.ResponseService;
import com.example.SwDeveloperServer.utils.response.SuccessStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags ="5. 투두 리스트 API ")
public class ToDoListController {

    private final ToDoListServiceImpl toDoListService;
    private final ResponseService responseService;
    private final JwtService jwtService;

    public ToDoListController(ToDoListServiceImpl toDoListService, ResponseService responseService, JwtService jwtService) {
        this.toDoListService = toDoListService;
        this.responseService = responseService;
        this.jwtService = jwtService;
    }

//    /**
//     * 투두 리스트  API
//     */
//    @ApiOperation(value = "회원 별 마이페이지 보기 ")
//    @ApiResponses({  // Response Message에 대한 Swagger 설명
//            @ApiResponse(code = 200, message = "OK", response = GetMyPage.class)
//    })
//    @GetMapping("/todo")
//    public ResponseEntity<?> getTodo(@RequestParam(required = false) String date) throws BaseException {
//        try {
//            Long userId = jwtService.getUserIdx();
//            GetMyPage getMyPage = toDoListService.getTodo(userId,date);
//            return responseService.successResult(getMyPage, SuccessStatus.SUCCESS);
//        } catch (BaseException exception) {
//            return responseService.errorResult(exception.getErrorStatus());
//        }
//    }

}
