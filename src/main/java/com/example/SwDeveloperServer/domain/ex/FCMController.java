package com.example.SwDeveloperServer.domain.ex;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class FCMController { //  프로젝트 테스트, 콘솔 확인, 알림 허용을 위한 컨트롤러

    private final FirebaseInit init;
    @GetMapping("/push")
    public String v1(){
        init.init();
        return "push";
    }
}
