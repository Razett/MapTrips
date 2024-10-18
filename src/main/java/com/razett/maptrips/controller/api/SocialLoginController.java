package com.razett.maptrips.controller.api;

import com.razett.maptrips.dto.UsersDTO;
import com.razett.maptrips.dto.social.NaverCode;
import com.razett.maptrips.service.api.SocialLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class SocialLoginController {

    private final SocialLoginService socialLoginService;

    /**
     * Naver Login API 인증 요청 CallBack
     * @return
     */
    @RequestMapping("/naver.do")
    public UsersDTO naverLoginApiCallback(NaverCode naverCode) {
        return socialLoginService.loginByNaver(naverCode);
    }
}
