package com.example.SwDeveloperServer.domain.toDoList.service;

import com.example.SwDeveloperServer.domain.toDoList.dto.*;
import com.example.SwDeveloperServer.domain.toDoList.entity.Todo;
import com.example.SwDeveloperServer.domain.toDoList.entity.TodoPhoto;
import com.example.SwDeveloperServer.domain.toDoList.repository.TodoPhotoRepository;
import com.example.SwDeveloperServer.domain.toDoList.repository.TodoRepository;
import com.example.SwDeveloperServer.domain.user.entity.User;
import com.example.SwDeveloperServer.domain.user.repository.UserJpaRepository;
import com.example.SwDeveloperServer.utils.response.BaseException;
import com.example.SwDeveloperServer.utils.response.ErrorStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ToDoListServiceImpl implements ToDoListService {

    private final UserJpaRepository userJpaRepository;
    private final TodoPhotoRepository todoPhotoRepository;
    private final TodoRepository todoRepository;

    public ToDoListServiceImpl(UserJpaRepository userJpaRepository, TodoPhotoRepository todoPhotoRepository, TodoRepository todoRepository) {
        this.userJpaRepository = userJpaRepository;
        this.todoPhotoRepository = todoPhotoRepository;
        this.todoRepository = todoRepository;
    }

    @Override
    public PostDailyTodoRes setDailyTodoList() throws BaseException, ParseException{
        List<User> getUsers = userJpaRepository.findAll();
        LocalDateTime now = LocalDateTime.now();
        now.with(LocalTime.MIN);
        for(int i=0; i<getUsers.size(); i++){
            createTodo(getUsers.get(i), now);
        }
        return new PostDailyTodoRes("데일리 목표가 설정되었습니다.");
    }

    private enum ETodo{
        e1("메일함 정리하기"),
        e2("노트북 전원 끄기"),
        e3("사진 용량 줄이기");
        private final String value;
        ETodo(String value){
            this.value = value;
        }
        public String getValue(){
            return value;
        }
    }

    private void createTodo(User user, LocalDateTime now) {
        int year = now.getYear();
        int month = now.getMonthValue();
        int dayOfMonth = now.getDayOfMonth();
        LocalDateTime startLocalDateTime = now.of(year,month,dayOfMonth,00,00,00);
        LocalDateTime endLocalDateTime = now.of(year,month,dayOfMonth,23,59,59);

        for(ETodo eTodo : ETodo.values()){
            Todo todo = new Todo();
            todo.setUser(user);
            todo.setGoalDescription(eTodo.getValue());
            todo.setToStartDate(startLocalDateTime);
            todo.setToEndDate(endLocalDateTime);
            todo.setState(1);
            todoRepository.save(todo);
        }
    }

    @Override
    public GetToDoListRes getTodo(Long userId, String date) throws ParseException, BaseException {
        Optional<User> user = userJpaRepository.findById(userId);
        List<Todo> getToDoList = todoRepository.findByUser(user.get());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        date = date+" 00:00:00";
        LocalDateTime getDate = LocalDateTime.parse(date,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            LocalDateTime getDate = sdf.parse(date);
        System.out.println(getDate);

        GetToDoListRes getToDoListRes = new GetToDoListRes();
        Queue<Todo> individualList = new LinkedList<>();

        for (int i = 0; i < getToDoList.size(); i++) {
            //compareTo메서드를 통한 날짜비교
            int compareStartDate = getDate.compareTo(getToDoList.get(i).getToStartDate());
            int compareEndDate = getDate.compareTo(getToDoList.get(i).getToStartDate());

            if(compareStartDate >=0 && compareEndDate <= 0){
                individualList.add(getToDoList.get(i));
            }
        }
        getToDoListRes.setIndividualList(individualList);
        return getToDoListRes;
    }

    @Override
    public PostTodoRes postPost(Long todoId, PostTodoReq postTodoReq) {
        Optional<Todo> getTodo = todoRepository.findById(todoId);
        Timestamp getTime = new Timestamp(System.currentTimeMillis());

        for(int i=0; i<postTodoReq.getQueue().size(); i++){
            TodoPhoto todoPhoto = new TodoPhoto();
            todoPhoto.setTodo(getTodo.get());
            todoPhoto.setToDoListPhotoUrl(postTodoReq.getQueue().poll());
            postTodoReq.getQueue().peek();
            todoPhoto.setCreatedAt(getTime);

            todoPhotoRepository.save(todoPhoto);
        }

        getTodo.get().setState(1);
        todoRepository.save(getTodo.get());

        return new PostTodoRes("인증완료되었습니다.");
    }

    @Override
    public PostToDoListRes setTodoList(Long userId, PostToDoListReq postToDoListReq) {
        Optional<User> user = userJpaRepository.findById(userId);
        Todo todo = new Todo();
        todo.setUser(user.get());
        todo.setGoalDescription(postToDoListReq.getGoalDescription());

        LocalDateTime startLocalDateTime = postToDoListReq.getToStartDate().atStartOfDay();
        LocalDateTime endLocalDateTime = postToDoListReq.getToStartDate().atTime(23,59, 59);
        todo.setToStartDate(startLocalDateTime);
        todo.setToEndDate(endLocalDateTime);
        todo.setState(1);

        todoRepository.save(todo);
        return new PostToDoListRes("투두에 등록되었습니다.");
    }

}
