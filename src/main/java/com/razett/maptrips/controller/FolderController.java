package com.razett.maptrips.controller;

import com.razett.maptrips.dto.NavbarSelector;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/folder")
@NoArgsConstructor
//@RequiredArgsConstructor
public class FolderController {

    /**
     * {@code folder = true;}
     */
    private final NavbarSelector NAVBAR = NavbarSelector.builder()
            .folder(true).topNavbar(true).bottomNavbar(true)
            .build();

    /**
     * 폴더 메인 페이지 (그리드)
     * @param model springframework.ui.Model
     * @return "/folder/grid.html"
     * @since 2024-10-09
     * @author JiwonJeong
     */
    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("navbar", NAVBAR);
        return "/folder/grid";
    }

    /**
     * 폴더 리스트 페이지
     * @param model springframework.ui.Model
     * @return "/folder/list.html"
     * @since 2024-10-09
     * @author JiwonJeong
     */
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("navbar", NAVBAR);
        return "/folder/list";
    }
}
