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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class InformationPostServiceImpl implements InformationPostService{

    private final UserJpaRepository userJpaRepository;
    private final InformationPostRepository informationPostRepository;
    private final InformationPostPhotoRepository informationPostPhotoRepository;

    public InformationPostServiceImpl(UserJpaRepository userJpaRepository, InformationPostRepository informationPostRepository, InformationPostPhotoRepository informationPostPhotoRepository) {
        this.userJpaRepository = userJpaRepository;
        this.informationPostRepository = informationPostRepository;
        this.informationPostPhotoRepository = informationPostPhotoRepository;
    }

    @Override
    public PostResultRes setInformationPost(Long userId, PostInfoReq postInfoReq) {
        Optional<User> user = userJpaRepository.findById(userId);

        InformationPost informationPost = new InformationPost(user.get(),
                postInfoReq.getInfoTitle(),
                postInfoReq.getInfoContent(),
                postInfoReq.getInfoTitlePhoto(),
                LocalDateTime.now(),
                0);
        informationPostRepository.save(informationPost);

        if(postInfoReq.getPhotoList().size() !=0){
            System.out.println(postInfoReq.getPhotoList().size());
            for(int i=0; i<postInfoReq.getPhotoList().size(); i++){
                // 오류
                InformationPostPhoto informationPostPhoto
                        = new InformationPostPhoto(informationPost, postInfoReq.getPhotoList().peek());
                postInfoReq.getPhotoList().remove(0);
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
            List<InformationPostPhoto> informationPostPhotos =  informationPostPhotoRepository.findByInformationPost(informationPosts.get(i));

            List<String> getPhoto = new LinkedList<>();

//            if(informationPostPhotos.size() != 0){
//                for(int j=0; j<informationPosts.size(); j++){
////                    System.out.println(informationPostPhotos.get(j).getInformationPostPhotoUrl());
//                    getPhoto.add(informationPostPhotos.get(j).getInformationPostPhotoUrl());
//                }
//            }

            GetInformRes getInformRes = new GetInformRes(informationPosts.get(i).getUser().getUserId(),
                    informationPosts.get(i).getUser().getEmail(),
                    informationPosts.get(i).getUser().getName(),
                    getPhoto,
                    informationPosts.get(i).getInformationPostId(),
                    informationPosts.get(i).getInfoTitle(),
                    informationPosts.get(i).getInfoContent(),
                    informationPosts.get(i).getInfoTitlePhoto(),
                    informationPosts.get(i).getInfoCreateTime(),
                    informationPosts.get(i).getInfoUpdateTime());
            getInformResList.add(getInformRes);
        }
        return getInformResList;
    }

    @Override
    public GetInformRes getInformationPost(Long userId, Long informationPostId) {
        Optional<InformationPost> informationPost = informationPostRepository.findById(informationPostId);

        List<InformationPostPhoto> informationPostPhotos =  informationPostPhotoRepository.findByInformationPost(informationPost.get());
        List<String> getPhoto = new LinkedList<>();
//            if(informationPostPhotos.size() != 0){
//                for(int j=0; j<informationPosts.size(); j++){
////                    System.out.println(informationPostPhotos.get(j).getInformationPostPhotoUrl());
//                    getPhoto.add(informationPostPhotos.get(j).getInformationPostPhotoUrl());
//                }
//            }

        GetInformRes getInformRes = new GetInformRes(informationPost.get().getUser().getUserId(),
                informationPost.get().getUser().getEmail(),
                informationPost.get().getUser().getName(),
                getPhoto,
                informationPost.get().getInformationPostId(),
                informationPost.get().getInfoTitle(),
                informationPost.get().getInfoContent(),
                informationPost.get().getInfoTitlePhoto(),
                informationPost.get().getInfoCreateTime(),
                informationPost.get().getInfoUpdateTime());
        return getInformRes;
    }
}
