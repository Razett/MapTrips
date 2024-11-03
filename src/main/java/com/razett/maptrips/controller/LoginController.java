package com.razett.maptrips.controller;

import com.razett.maptrips.dto.NavbarSelector;
import com.razett.maptrips.dto.user.SignUpUser;
import com.razett.maptrips.dto.user.UsersDTO;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 로그인 및 회원가입 Controller
 *
 * @since 2024-10-06 v1.0.0
 * @author jeongjiwon
 * @version 1.0.0
 */
@Controller
@RequestMapping("/login")
@NoArgsConstructor
@SessionAttributes("SignUpUser")
//@RequiredArgsConstructor
public class LoginController {

    /**
     * Hide All Navbars
     */
    private final NavbarSelector NAVBAR = NavbarSelector.builder()
            .topNavbar(false).bottomNavbar(false)
            .build();

    /**
     * 단계별 회원가입 기능 구현을 위해 회원가입 시 입력한 정보를 저장할 객체를 세션에 저장합니다.
     *
     * @return SignUpUser Object
     * @since 2024-10-25 v1.0.0
     * @author jeongjiwon
     * @version 1.0.0
     */
    @ModelAttribute("SignUpUser")
    public SignUpUser setupSignUp() {
        return new SignUpUser();
    }

    /**
     * 1. 이용약관 동의
     * @return
     * @since 2024-10-25 v1.0.0
     * @author jeongjiwon
     * @version 1.0.0
     */
    @GetMapping("/signup/page0")
    private String signupPage0(Model model, SignUpUser signUpUser) {
        model.addAttribute("navbar", NAVBAR);

        return "/login/signup/page0";
    }

    /**
     * 1. 이용약관 동의 완료 시, 필수 이용약관을 모두 동의 하였는지 확인합니다.
     * @return 동의 시 page1 redirect, 오류 시 page0
     * @since 2024-10-25 v1.0.0
     * @author jeongjiwon
     * @version 1.0.0
     */
    @PostMapping("/signup/page0")
    public String signupPage0Process(@Valid @ModelAttribute("SignUpUser") SignUpUser signUpUser, BindingResult bindingResult, Model model) {
        model.addAttribute("navbar", NAVBAR);

        if (bindingResult.hasFieldErrors("termsReq")) {
            model.addAttribute("msg", bindingResult.getFieldError("termsReq").getDefaultMessage());
            return "/login/signup/page0";
        }
        return "redirect:/login/signup/page1";
    }

    /**
     * 2. 이메일 인증
     * @return 이전 단계 진입 없이 접근할 경우 이용약관 동의를 반환, 그 외 page1
     * @since 2024-10-25 v1.0.0
     * @author jeongjiwon
     * @version 1.0.0
     */
    @GetMapping("/signup/page1")
    public String signupPage1(@Valid @ModelAttribute("SignUpUser") SignUpUser signUpUser, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("navbar", NAVBAR);
        System.out.println(signUpUser);

        if (bindingResult.hasFieldErrors("termsReq") || signUpUser.getTermsReq() == null) {
            redirectAttributes.addFlashAttribute("msg", "잘못된 접근입니다.");
            return "redirect:/login/signup/page0";
        }
        return "/login/signup/page1";
    }

    /**
     * 2. 이메일 인증 완료 시, 이메일 주소의 문제가 없는지 서버에서 다시 확인합니다.
     * @return 완료 시 page2 redirect, 오류 시 page1
     * @since 2024-10-25 v1.0.0
     * @author jeongjiwon
     * @version 1.0.0
     */
    @PostMapping("/signup/page1")
    public String signupPage1Process(@Valid @ModelAttribute("SignUpUser") SignUpUser signUpUser, BindingResult bindingResult, Model model) {
        model.addAttribute("navbar", NAVBAR);

        if (bindingResult.hasFieldErrors("email")) {
            model.addAttribute("msg", bindingResult.getFieldError("email").getDefaultMessage());
            return "/login/signup/page1";
        }

        return "redirect:/login/signup/page2";
    }

    /**
     * 3. 암호 설정
     * @return 이전 단계 진입 없이 접근할 경우 이용약관 동의를 반환, 그 외 page2
     * @since 2024-10-25 v1.0.0
     * @author jeongjiwon
     * @version 1.0.0
     */
    @GetMapping("/signup/page2")
    public String signupPage2(@Valid @ModelAttribute("SignUpUser") SignUpUser signUpUser, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("navbar", NAVBAR);

        if (bindingResult.hasFieldErrors("email")) {
            redirectAttributes.addFlashAttribute("msg", "잘못된 접근입니다.");
            return "redirect:/login/signup/page0";
        }
        return "/login/signup/page2";
    }

    /**
     * 3. 암호 설정 완료 시, 암호의 유효성을 서버에서 다시 확인합니다.
     * @return 완료 시 page3 redirect, 오류 시 page2
     * @since 2024-10-25 v1.0.0
     * @author jeongjiwon
     * @version 1.0.0
     */
    @PostMapping("/signup/page2")
    public String signupPage2Process(@Valid @ModelAttribute("SignUpUser") SignUpUser signUpUser, BindingResult bindingResult, Model model) {
        model.addAttribute("navbar", NAVBAR);

        if (bindingResult.hasFieldErrors("password")) {
            model.addAttribute("msg", bindingResult.getFieldError("password").getDefaultMessage());
            return "/login/signup/page2";
        }

        return "redirect:/login/signup/page3";
    }

    /**
     * 4. username 및 이름 설정
     * @return 이전 단계 진입 없이 접근할 경우 이용약관 동의를 반환, 그 외 page3
     * @since 2024-10-25 v1.0.0
     * @author jeongjiwon
     * @version 1.0.0
     */
    @GetMapping("/signup/page3")
    public String signupPage3(@Valid @ModelAttribute("SignUpUser") SignUpUser signUpUser, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("navbar", NAVBAR);

        if (bindingResult.hasFieldErrors("password")) {
            redirectAttributes.addFlashAttribute("msg", "잘못된 접근입니다.");
            return "redirect:/login/signup/page0";
        }
        return "/login/signup/page3";
    }

    /**
     * 4. username 설정 완료 시 username의 유효성을 서버에서 다시확인하며, 최종적으로 DB에 저장합니다.
     * @return 완료 시 login redirect, 오류 시 page2
     * @since 2024-10-25 v1.0.0
     * @author jeongjiwon
     * @version 1.0.0
     */
    @PostMapping("/signup/page3")
    private String signupPage3Process(@Valid @ModelAttribute("SignUpUser") SignUpUser signUpUser, BindingResult bindingResult, Model model) {
        model.addAttribute("navbar", NAVBAR);

        if (bindingResult.hasFieldErrors("username")) {
            model.addAttribute("msg", bindingResult.getFieldError("username").getDefaultMessage());
            return "/login/signup/page3";
        }

        return "redirect:/login";
    }
}
