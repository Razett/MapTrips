package com.razett.maptrips.controller;

import com.razett.maptrips.dto.NavbarSelector;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
@NoArgsConstructor
//@RequiredArgsConstructor
public class MyPageController {

    /**
     * {@code mypage = true;} {@code topNavbar = true;} {@code bottomNavbar = true;}
     */
    private final NavbarSelector NAVBAR = NavbarSelector.builder()
            .mypage(true).topNavbar(true).bottomNavbar(true)
            .build();

    /**
     * {@code mypage = true;} {@code topNavbar = true;} {@code bottomNavbar = false;}
     */
    private final NavbarSelector SettingNavBar = NavbarSelector.builder()
            .mypage(true).topNavbar(true).bottomNavbar(false)
            .build();


    /**
     * 내 정보 메인 페이지
     * @param model springframework.ui.Model
     * @return "/mypage/mainpage.html"
     * @since 2024-10-09
     * @author JiwonJeong
     */
    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("navbar", NAVBAR);
        model.addAttribute("username", "username01");
        return "/mypage/mainpage";
    }

    /**
     * 설정 메인 페이지
     * @param model springframework.ui.Model
     * @return "/mypage/setting/mainpage.html"
     * @since 2024-10-10
     * @author JiwonJeong
     */
    @GetMapping("/setting/")
    public String setting(Model model) {
        model.addAttribute("navbar", NAVBAR);
        model.addAttribute("username", "username01");
        return "/mypage/setting/mainpage";
    }
}
