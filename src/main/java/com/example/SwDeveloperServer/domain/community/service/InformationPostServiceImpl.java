package com.example.SwDeveloperServer.domain.community.service;

import com.example.SwDeveloperServer.domain.community.dto.req.PostInfoReq;
import com.example.SwDeveloperServer.domain.community.dto.res.GetInformRes;
import com.example.SwDeveloperServer.domain.community.dto.res.PostResultRes;
import com.example.SwDeveloperServer.domain.community.entity.InformationPost;
import com.example.SwDeveloperServer.domain.community.entity.InformationPostPhoto;
import com.example.SwDeveloperServer.domain.community.repository.InformationPostPhotoRepository;
import com.example.SwDeveloperServer.domain.community.repository.InformationPostRepository;
import com.example.SwDeveloperServer.domain.user.entity.User;
import com.example.SwDeveloperServer.domain.user.repository.UserJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class InformationPostServiceImpl implements InformationPostService{

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserJpaRepository userJpaRepository;
    private final InformationPostRepository informationPostRepository;
    private final InformationPostPhotoRepository informationPostPhotoRepository;

    public InformationPostServiceImpl(UserJpaRepository userJpaRepository, InformationPostRepository informationPostRepository, InformationPostPhotoRepository informationPostPhotoRepository) {
        this.userJpaRepository = userJpaRepository;
        this.informationPostRepository = informationPostRepository;
        this.informationPostPhotoRepository = informationPostPhotoRepository;
    }

    @Override
    @Transactional
    public PostResultRes setInformationPost(Long userId, PostInfoReq postInfoReq) {
        Optional<User> user = userJpaRepository.findById(userId);
        InformationPost informationPost = new InformationPost(user.get(), postInfoReq);
        informationPostRepository.save(informationPost);

        int size = postInfoReq.getPhotoList().size();
        if(size != 0){
            for(int i=0; i<size; i++){
                InformationPostPhoto informationPostPhoto
                        = new InformationPostPhoto(informationPost, postInfoReq.getPhotoList().poll());
                informationPostPhotoRepository.save(informationPostPhoto);
            }
        }
        return new PostResultRes(informationPost.getInformationPostId(), "정보글이 등록되었습니다.");
    }

    @Override
    public List<GetInformRes> getInformationPosts(Long userId) {
        List<InformationPost> informationPosts = informationPostRepository.findAll();
        List<GetInformRes> getInformResList = new ArrayList<>();
        for(int i=0; i<informationPosts.size(); i++){
            List<String> getPhoto = getPhotos(informationPosts.get(i));
            GetInformRes getInformRes = new GetInformRes(informationPosts.get(i),getPhoto);
            getInformResList.add(getInformRes);
        }
        return getInformResList;
    }

    @Override
    public GetInformRes getInformationPost(Long userId, Long informationPostId) {
        Optional<InformationPost> informationPost = informationPostRepository.findById(informationPostId);
        List<String> getPhoto = getPhotos(informationPost.get());
        GetInformRes getInformRes = new GetInformRes(informationPost.get(),getPhoto);
        return getInformRes;
    }

    public List<String> getPhotos(InformationPost informationPost){
        List<String> getPhotos = new LinkedList<>();
        List<InformationPostPhoto> informationPostPhotos =  informationPostPhotoRepository.findByInformationPost(informationPost);
        int size = informationPostPhotos.size();
        if(size != 0){
            for(int i=0; i<size; i++){
                getPhotos.add(informationPostPhotos.get(i).getInformationPostPhotoUrl());
            }
        }
        return getPhotos;
    }
}
