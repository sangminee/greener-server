package com.example.SwDeveloperServer.domain.user.service;

import com.example.SwDeveloperServer.domain.myPage.entity.Point;
import com.example.SwDeveloperServer.domain.myPage.repository.PointRepository;
import com.example.SwDeveloperServer.domain.shop.entity.Item;
import com.example.SwDeveloperServer.domain.shop.entity.UserItem;
import com.example.SwDeveloperServer.domain.shop.repository.ItemRepository;
import com.example.SwDeveloperServer.domain.shop.repository.UserItemRepository;
import com.example.SwDeveloperServer.domain.toDoList.entity.Todo;
import com.example.SwDeveloperServer.domain.toDoList.repository.TodoRepository;
import com.example.SwDeveloperServer.domain.user.entity.PlantPhoto;
import com.example.SwDeveloperServer.domain.user.entity.User;
import com.example.SwDeveloperServer.domain.user.repository.PlantPhotoRepository;
import com.example.SwDeveloperServer.domain.user.repository.UserRepository;
import com.example.SwDeveloperServer.domain.user.dto.request.PostJoinReq;
import com.example.SwDeveloperServer.domain.user.dto.response.PostFindEmailRes;
import com.example.SwDeveloperServer.domain.user.dto.response.PostFindPasswordRes;
import com.example.SwDeveloperServer.domain.user.dto.response.PostJoinRes;
import com.example.SwDeveloperServer.domain.user.dto.request.PostLoginReq;
import com.example.SwDeveloperServer.domain.user.dto.response.PostLoginRes;
import com.example.SwDeveloperServer.utils.jwt.JwtService;
import com.example.SwDeveloperServer.utils.response.BaseException;
import com.example.SwDeveloperServer.utils.response.ErrorStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.SwDeveloperServer.utils.response.ErrorStatus.*;

@Service
public class UserServiceImpl implements UerService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userJpaRepository;
    private final UserItemRepository userItemListRepository;
    private final JwtService jwtService;
    private final ItemRepository itemRepository;
    private final PlantPhotoRepository plantPhotoRepository;
    private final PointRepository pointRepository;

    private final TodoRepository todoRepository;

    public UserServiceImpl(UserRepository userJpaRepository, UserItemRepository userItemListRepository,
                           JwtService jwtService, ItemRepository itemRepository, PlantPhotoRepository plantPhotoRepository,
                           PointRepository pointRepository, TodoRepository todoRepository) {
        this.userJpaRepository = userJpaRepository;
        this.userItemListRepository = userItemListRepository;
        this.jwtService = jwtService;
        this.itemRepository = itemRepository;
        this.plantPhotoRepository = plantPhotoRepository;
        this.pointRepository = pointRepository;
        this.todoRepository = todoRepository;
    }

    @Override
    public PostJoinRes join(PostJoinReq postJoinReq) throws BaseException {
        User user = postJoinReq.toEntity();

        Optional<PlantPhoto> getPlantPhoto = plantPhotoRepository.findById((long)1);
        user.setPlantPhoto(getPlantPhoto.get());

        userJpaRepository.save(user);

        createTodo(user, user.getUserCreateTime());
        createPoint(user);

        List<Item> allItem = itemRepository.findAll();
        for(int i=0; i<allItem.size(); i++){
            Long itemId = allItem.get(i).getItemId();
            createItem(user, itemId);
        }

        return new PostJoinRes(user.getUserId(), "회원가입이 완료되었습니다.");
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

        System.out.println("dddd");
        for(ETodo eTodo : ETodo.values()){
            Todo todo = new Todo();
            todo.setUser(user);
            todo.setGoalDescription(eTodo.value);

            todo.setToStartDate(startLocalDateTime);
            todo.setToEndDate(endLocalDateTime);

            todo.setState(1);

            todoRepository.save(todo);
        }
    }

    // 회원 별 포인트 DB 생성
    public void createPoint(User user){
        Point point = new Point();
        point.setUser(user);
        point.setPointValue(1000);  // 기본 포인트

        pointRepository.save(point);
    }
    // 식물
    public void createItem(User user, Long itemId){
        UserItem userItemList = new UserItem();
        userItemList.setUser(user);

        Optional<Item> item = itemRepository.findById(itemId);
        userItemList.setItem(item.get());
        userItemList.setItemQuantity(0);

        userItemListRepository.save(userItemList);
    }

    @Override
    public PostLoginRes login(PostLoginReq postLoginReq) throws BaseException {
            Optional<User> user = userJpaRepository.findByEmail(postLoginReq.getEmail());
            if(user.isEmpty()){
                logger.error("이메일 오류");
                throw new BaseException(ErrorStatus.POST_USERS_EMPTY_EMAIL);
            }
            if(!user.get().getPassword().equals(postLoginReq.getPassword())){
                logger.error("비밀번호 오류");
                throw new BaseException(ErrorStatus.INVALID_USER_PASSWORD);
            }
            // jwt 발급
            String jwt = jwtService.createJwt(user.get().getUserId());
            return new PostLoginRes(jwt,"로그인을 성공했습니다.");
    }

    @Override
    public PostFindEmailRes findEmail(String phone) throws BaseException {
        Optional<User> user = userJpaRepository.findByPhone(phone);
        if(user.get() == null){
            throw new BaseException(INVALID_USER_PHONE);
        }else{
            PostFindEmailRes postFindEmailRes = new PostFindEmailRes(user.get().getEmail());
            return postFindEmailRes;
        }
    }

    @Override
    public PostFindPasswordRes findPassword(String email) throws BaseException{
        Optional<User> user = userJpaRepository.findByEmail(email);
        if(user.get() == null){
            throw new BaseException(INVALID_USER_EMAIL);
        }else{
            PostFindPasswordRes postFindPasswordRes = new PostFindPasswordRes(user.get().getPassword());
            return postFindPasswordRes;
        }
    }

}
