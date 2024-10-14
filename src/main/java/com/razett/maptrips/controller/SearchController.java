package com.razett.maptrips.controller;

import com.razett.maptrips.dto.NavbarSelector;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Search Controller
 * @since 2024-10-06
 * @author jeongjiwon
 * @version 1.0.0
 */
@Controller
@RequestMapping("/search")
@NoArgsConstructor
//@RequiredArgsConstructor
public class SearchController {

    /**
     * {@code search = true;}
     */
    private final NavbarSelector NAVBAR = NavbarSelector.builder()
            .search(true).topNavbar(true).bottomNavbar(true)
            .build();

    /**
     * 검색 메인 페이지
     * @param model springframework.ui.Model
     * @return "/search/mainpage.html"
     * @since 2024-10-06 v1.0.0
     * @author JiwonJeong
     */
    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("navbar", NAVBAR);

        return "/search/mainpage";
    }
}
