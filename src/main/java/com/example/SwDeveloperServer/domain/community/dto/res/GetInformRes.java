package com.example.SwDeveloperServer.domain.community.dto.res;

import com.example.SwDeveloperServer.domain.community.entity.InformationPostPhoto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Queue;

@Getter
@Setter
@AllArgsConstructor
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
}
