package com.razett.maptrips.controller.api;

import com.razett.maptrips.dto.user.Verification;
import com.razett.maptrips.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Users객체와 관련한 API Controller
 *
 * @since 2024-10-19 v1.0.0
 * @author JiwonJeong
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    /**
     * 이메일 중복 여부를 확인합니다.
     * @param email 중복 여부를 확인할 이메일
     * @return 사용가능 여부
     *
     * @since 2024-10-19 v1.0.0
     * @author JiwonJeong
     */
    @RequestMapping(method = RequestMethod.POST, value = "/check/email")
    public ResponseEntity<Boolean> isAvailableEmail(@RequestBody String email) {
        String emailFormatted = email.replaceAll("\"", "").trim();
        return ResponseEntity.ok(userService.isAvailableEmail(email));
    }

    /**
     * email 주소로 인증코드를 발송합니다.
     * @param email 인증코드를 발송할 이메일 주소
     * @return 발송 성공 여부
     *
     * @since 2024-10-19 v1.0.0
     * @author JiwonJeong
     */
    @RequestMapping(method = RequestMethod.POST, value = "/sendcode")
    public ResponseEntity<Boolean> sendVerificationCode(@RequestBody String email) {
        String emailFormatted = email.replaceAll("\"", "").trim();
        System.out.println(emailFormatted);
        return ResponseEntity.ok(userService.sendVerificationCode(emailFormatted));
    }

    /**
     * 이메일 주소로 발송한 인증코드를 사용자가 입력한 인증코드와 대조합니다.
     * @param verification 인증코드를 발송한 이메일 주소와 사용자가 입력한 인증코드를 저장한 객체
     * @return 인증 성공 여부를 저장한 객체
     *
     * @since 2024-10-19 v1.0.0
     * @author JiwonJeong
     */
    @RequestMapping(method = RequestMethod.POST, value = "/verifycode")
    public ResponseEntity<Verification> VerifyCode(@RequestBody Verification verification) {
        System.out.println(verification);
        return ResponseEntity.ok(userService.verifiedCode(verification));
    }

    /**
     * username 중복 여부를 확인합니다.
     * @param username 중복 여부를 확인할 username
     * @return 사용가능 여부
     *
     * @since 2024-10-20 v1.0.0
     * @author JiwonJeong
     */
    @RequestMapping(method = RequestMethod.POST, value = "/check/username")
    public ResponseEntity<Boolean> isAvailableUsername(@RequestBody String username) {
        return ResponseEntity.ok(userService.isAvailableEmail(username));
    }
}
