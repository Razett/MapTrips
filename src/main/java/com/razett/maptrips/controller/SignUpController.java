package com.razett.maptrips.controller;

import com.razett.maptrips.dto.NavbarSelector;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/signup")
@NoArgsConstructor
//@RequiredArgsConstructor
public class SignUpController {

    /**
     * Hide All Navbars
     */
    private final NavbarSelector NAVBAR = NavbarSelector.builder()
            .topNavbar(false).bottomNavbar(false)
            .build();


}
