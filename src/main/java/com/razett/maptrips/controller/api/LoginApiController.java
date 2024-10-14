package com.razett.maptrips.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginApiController {

    /**
     * Naver Login API 인증 요청 CallBack
     * @return
     */
    @RequestMapping("naver.do")
    public String naverLoginApiCallback() {

        return "";
    }
}
