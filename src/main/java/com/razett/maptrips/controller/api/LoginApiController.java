package com.razett.maptrips.controller.api;

import com.razett.maptrips.dto.user.UsersDTO;
import com.razett.maptrips.dto.social.NaverCode;
import com.razett.maptrips.service.UserService;
import com.razett.maptrips.service.api.SocialLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginApiController {

    private final SocialLoginService socialLoginService;
    private final UserService userService;

    /**
     * Naver Login API 인증 요청 CallBack
     * @return 네이버 로그인 오류 시 로그인 페이지, 회원이 아닐 경우 회원가입 페이지, 회원일 시 피드.
     */
    @RequestMapping("/naver.do")
    public RedirectView naverLoginApiCallback(NaverCode naverCode, RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView();

        Optional<UsersDTO> optNaverUser = socialLoginService.getUserByNaver(naverCode);
        if (optNaverUser.isPresent()) {
            Optional<UsersDTO> optExistUser = userService.readByNaverId(optNaverUser.get().getNaverId());

            if (optExistUser.isPresent()) {
                redirectView.setUrl("/login");
                // 로그인 구현
            } else {
                redirectView.setUrl("/login/signup/page0");
                redirectAttributes.addFlashAttribute("msg", optNaverUser.get().toString());
                redirectAttributes.addFlashAttribute("naverUser", optNaverUser.get());
                // 회원 가입으로 이동
            }
        } else {
            redirectView.setUrl("/login");
            redirectAttributes.addFlashAttribute("msg", "네이버 로그인 정보를 가져오는데 실패하였습니다.");
        }
        return redirectView;
    }
}
