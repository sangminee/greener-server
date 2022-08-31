package com.example.SwDeveloperServer.domain.user.service;

import com.example.SwDeveloperServer.domain.user.repository.dto.PostJoinReq;
import com.example.SwDeveloperServer.domain.user.repository.dto.PostJoinRes;
import com.example.SwDeveloperServer.domain.user.repository.dto.PostLoginReq;
import com.example.SwDeveloperServer.domain.user.repository.dto.PostLoginRes;

public interface UerService {

    PostJoinRes join(PostJoinReq postJoinReq);

    PostLoginRes login(PostLoginReq postLoginReq);
}
