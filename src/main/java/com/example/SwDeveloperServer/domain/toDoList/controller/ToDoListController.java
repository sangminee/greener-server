package com.example.SwDeveloperServer.domain.toDoList.controller;

import com.example.SwDeveloperServer.domain.toDoList.dto.*;
import com.example.SwDeveloperServer.domain.toDoList.entity.Todo;
import com.example.SwDeveloperServer.domain.toDoList.service.ToDoListServiceImpl;
import com.example.SwDeveloperServer.utils.jwt.JwtService;
import com.example.SwDeveloperServer.utils.response.BaseException;
import com.example.SwDeveloperServer.utils.response.ResponseService;
import com.example.SwDeveloperServer.utils.response.SuccessStatus;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

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

    /**
     * 투두 리스트 개인 목표 추가 API
     */
    @ApiOperation(value = "투두 리스트 개인 목표 추가")
    @ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = PostDailyTodoRes.class)
    })
    @PostMapping("/todo-daily")
    @Scheduled(cron ="0 0 0 * * *")
    public ResponseEntity<?> setDailyTodoList() throws BaseException {

        try {
            PostDailyTodoRes postDailyTodoRes = toDoListService.setDailyTodoList();
            return responseService.successResult(postDailyTodoRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 투두 리스트 추가  API
     */
    @ApiOperation(value = "투두 리스트 추가")
    @ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = PostToDoListRes.class)
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @PostMapping("/todo")
    public ResponseEntity<?> setTodoList(@RequestBody PostToDoListReq postToDoListReq) throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            PostToDoListRes postToDoListRes = toDoListService.setTodoList(userId, postToDoListReq);
            return responseService.successResult(postToDoListRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    /**
     * 투두 리스트  API
     */
    @ApiOperation(value = "투두 리스트 목록보기 ")
    @ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = GetToDoListRes.class)
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @GetMapping("/todo")
    public ResponseEntity<?> getTodo(@RequestParam(required = false) String date) throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            GetToDoListRes getToDoListRes = toDoListService.getTodo(userId,date);
            return responseService.successResult(getToDoListRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 투두 리스트 인증 API
     */
    @ApiOperation(value = "투두 리스트 인증")
    @ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = PostTodoRes.class)
    })
    @ApiImplicitParams(@ApiImplicitParam(name="JWT", value = "X-ACCESS-TOKEN", required = true, dataType = "string", paramType = "header"))
    @PostMapping("/todo/{todoId}")
    public ResponseEntity<?> postPost(@PathVariable Long todoId, @RequestBody PostTodoReq postTodoReq){
        try {
            Long userId = jwtService.getUserIdx();
            PostTodoRes postTodoRes = toDoListService.postPost(todoId,postTodoReq);
            return responseService.successResult(postTodoRes, SuccessStatus.SUCCESS);
        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

}
