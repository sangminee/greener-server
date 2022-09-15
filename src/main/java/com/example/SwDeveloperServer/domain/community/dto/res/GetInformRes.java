package com.example.SwDeveloperServer.domain.community.dto.res;

import com.example.SwDeveloperServer.domain.community.entity.InformationPost;
import com.example.SwDeveloperServer.domain.community.entity.InformationPostPhoto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Queue;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetInformRes {

    private Long userId;
    private String email;
    private String name;

    private List<String> infoPhotos;
//    private List<InformationPostPhoto> infoPhotos;

    private Long informationPostId;
    private String infoTitle;
    private String infoContent;
    private String infoTitlePhoto;
    private LocalDateTime infoCreateTime;
    private LocalDateTime infoUpdateTime;

    public GetInformRes(InformationPost informationPost,List<String> infoPhotos){
        this.userId=informationPost.getUser().getUserId();
        this.email=informationPost.getUser().getEmail();
        this.name=informationPost.getUser().getName();
        this.infoPhotos=infoPhotos;
        this.informationPostId=informationPost.getInformationPostId();
        this.infoTitle=informationPost.getInfoTitle();
        this.infoContent=informationPost.getInfoContent();
        this.infoTitlePhoto=informationPost.getInfoTitlePhoto();
        this.infoCreateTime=informationPost.getInfoCreateTime();
        this.infoUpdateTime=informationPost.getInfoUpdateTime();
    }
}
