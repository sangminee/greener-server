package com.example.SwDeveloperServer.domain.user.service;

import com.example.SwDeveloperServer.domain.user.entity.User;
import com.example.SwDeveloperServer.domain.user.repository.UserJpaRepository;
import com.example.SwDeveloperServer.domain.user.repository.dto.request.PostJoinReq;
import com.example.SwDeveloperServer.domain.user.repository.dto.response.PostFindEmailRes;
import com.example.SwDeveloperServer.domain.user.repository.dto.response.PostFindPasswordRes;
import com.example.SwDeveloperServer.domain.user.repository.dto.response.PostJoinRes;
import com.example.SwDeveloperServer.domain.user.repository.dto.request.PostLoginReq;
import com.example.SwDeveloperServer.domain.user.repository.dto.response.PostLoginRes;
import com.example.SwDeveloperServer.utils.jwt.JwtService;
import com.example.SwDeveloperServer.utils.response.BaseException;
import com.example.SwDeveloperServer.utils.response.ErrorStatus;
import com.example.SwDeveloperServer.utils.response.ResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.SwDeveloperServer.utils.response.ErrorStatus.*;

@Service
public class UserServiceImpl implements UerService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserJpaRepository userJpaRepository;
    private final JwtService jwtService;

    public UserServiceImpl(UserJpaRepository userJpaRepository, JwtService jwtService) {
        this.userJpaRepository = userJpaRepository;
        this.jwtService = jwtService;
    }

    @Override
    public PostJoinRes join(PostJoinReq postJoinReq) throws BaseException {

        User user = new User();
        user.setEmail(postJoinReq.getEmail());
        user.setPassword(postJoinReq.getPassword());
        user.setName(postJoinReq.getName());
        user.setNickname(postJoinReq.getNickname());
        user.setUserPhotoUrl(postJoinReq.getUserPhotoUrl());
        user.setPhone(postJoinReq.getPhone());
        user.setUserCreateTime(LocalDateTime.now());

        user.setUserServiceAgreement(postJoinReq.getUserServiceAgreement());
        user.setPhoneAgreement(postJoinReq.getPhoneAgreement());
        user.setUserType(postJoinReq.getUserType());
        user.setState(postJoinReq.getState());

        userJpaRepository.save(user);

        return new PostJoinRes(user.getUserId(), "회원가입이 완료되었습니다.");
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
