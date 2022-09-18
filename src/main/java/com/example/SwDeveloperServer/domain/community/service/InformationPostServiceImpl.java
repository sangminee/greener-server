package com.example.SwDeveloperServer.domain.community.service;

import com.example.SwDeveloperServer.domain.community.dto.req.PostInfoReq;
import com.example.SwDeveloperServer.domain.community.dto.res.DeleteResultRes;
import com.example.SwDeveloperServer.domain.community.dto.res.GetInformRes;
import com.example.SwDeveloperServer.domain.community.dto.res.PostResultRes;
import com.example.SwDeveloperServer.domain.community.entity.InformationPost;
import com.example.SwDeveloperServer.domain.community.entity.InformationPostPhoto;
import com.example.SwDeveloperServer.domain.community.entity.InformationPostScrap;
import com.example.SwDeveloperServer.domain.community.repository.InformationPostPhotoRepository;
import com.example.SwDeveloperServer.domain.community.repository.InformationPostRepository;
import com.example.SwDeveloperServer.domain.community.repository.InformationPostScrapRepository;
import com.example.SwDeveloperServer.domain.user.entity.User;
import com.example.SwDeveloperServer.domain.user.repository.UserJpaRepository;
import com.example.SwDeveloperServer.utils.response.BaseException;
import com.example.SwDeveloperServer.utils.response.ErrorStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class InformationPostServiceImpl implements InformationPostService{

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserJpaRepository userJpaRepository;
    private final InformationPostRepository informationPostRepository;
    private final InformationPostPhotoRepository informationPostPhotoRepository;
    private final InformationPostScrapRepository informationPostScrapRepository;

    public InformationPostServiceImpl(UserJpaRepository userJpaRepository, InformationPostRepository informationPostRepository,
                                      InformationPostPhotoRepository informationPostPhotoRepository, InformationPostScrapRepository informationPostScrapRepository) {
        this.userJpaRepository = userJpaRepository;
        this.informationPostRepository = informationPostRepository;
        this.informationPostPhotoRepository = informationPostPhotoRepository;
        this.informationPostScrapRepository = informationPostScrapRepository;
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

    @Override
    public PostResultRes setInformationPostScrap(Long userId, Long informationPostId) throws BaseException {
        Optional<User> user = userJpaRepository.findById(userId);
        Optional<InformationPost> informationPost = informationPostRepository.findById(informationPostId);
        List<InformationPostScrap> getScrap = informationPostScrapRepository.findByInformationPost(informationPost.get());
        for(int i=0; i<getScrap.size(); i++){
            if(getScrap.get(i).getUser().equals(user.get())){
                throw new BaseException(ErrorStatus.EXIST_INFO_POST_SCRAP);
            }
        }
        InformationPostScrap informationPostScrap = new InformationPostScrap(user.get(), informationPost.get());
        informationPostScrapRepository.save(informationPostScrap);
        return new PostResultRes(informationPostScrap.getInformationPostScrapId(),"스크랩 완료되었습니다.");
    }

    @Override
    public DeleteResultRes deleteInformationPostScrap(Long userId, Long informationPostScrapId) throws BaseException {
        Optional<User> user = userJpaRepository.findById(userId);
        Optional<InformationPostScrap> informationPostScrap = informationPostScrapRepository.findById(informationPostScrapId);

        if(!informationPostScrap.get().getUser().equals(user.get())){
            throw new BaseException(ErrorStatus.INVALID_INFO_POST_SCRAP_USER);
        }
        DeleteResultRes deleteResultRes = new DeleteResultRes(informationPostScrap.get().getInformationPostScrapId());
        informationPostScrapRepository.delete(informationPostScrap.get());
        return deleteResultRes;
    }
}
