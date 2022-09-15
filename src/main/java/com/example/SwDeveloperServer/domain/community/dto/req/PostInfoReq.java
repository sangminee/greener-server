package com.example.SwDeveloperServer.domain.community.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Queue;

@Getter
@Setter
@AllArgsConstructor
public class PostInfoReq {
    private String infoTitle;
    private String infoContent;
    private String infoTitlePhoto;

    private Queue<String> photoList;
}
