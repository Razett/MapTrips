package com.razett.maptrips.controller;

import com.razett.maptrips.dto.NavbarSelector;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@NoArgsConstructor
//@RequiredArgsConstructor
public class MainController {

    /**
     * {@code home = true;}
     */
    private final NavbarSelector NAVBAR = NavbarSelector.builder()
            .home(true).topNavbar(true).bottomNavbar(true)
            .build();

    /**
     * / (ROOT) 페이지
     * @param model springframework.ui.Model
     * @return "/index.html"
     * @since 2024-10-06
     * @author JiwonJeong
     */
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("navbar", NAVBAR);

        return "/index";
    }


    /**
     * 로그인 페이지
     * @param model springframework.ui.Model
     * @param source 이전 페이지 위치
     * @return "/login.html"
     * @since 2024-10-09
     * @author JiwonJeong
     */
    @GetMapping("/login")
    public String login(Model model, String source) {
        NavbarSelector loginNavBar = NavbarSelector.builder().topNavbar(false).bottomNavbar(false).build();
        model.addAttribute("navbar", loginNavBar);
        model.addAttribute("source", source);

        return "/login";
    }
}
