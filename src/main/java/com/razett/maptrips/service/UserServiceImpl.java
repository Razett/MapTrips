package com.razett.maptrips.service;

import com.razett.maptrips.dto.user.UsersDTO;
import com.razett.maptrips.dto.user.Verification;
import com.razett.maptrips.entity.user.Users;
import com.razett.maptrips.mapper.user.UsersMapper;
import com.razett.maptrips.repository.UsersRepository;
import com.razett.maptrips.util.RedisUtils;
import com.razett.maptrips.util.SmtpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

    private final UsersMapper usersMapper;

    private final SmtpUtils smtpUtils;
    private final RedisUtils redisUtils;

    private static String AUTH_CODE_PREFIX = "AUTH_CODE_";

    /**
     * email 주소의 중복을 확인합니다.
     * @param email 입력한 이메일 주소
     * @return 사용가능 여부 true, false
     *
     * @since 2024-10-18 v1.0.0
     * @author JiwonJeong
     */
    @Override
    public boolean isAvailableEmail(String email) {
        return usersRepository.findByEmail(email).isEmpty();
    }

    /**
     * email 주소로 인증 코드를 전송합니다.
     * @param email 인증 코드를 전송할 이메일 주소
     * @return 전송 성공 여부
     *
     * @since 2024-10-18 v1.0.0
     * @author JiwonJeong
     */
    @Override
    public boolean sendVerificationCode(String email) {
        if (isAvailableEmail(email)) {
            String code = createCode();
            String subject = "MapTrips 이메일 인증번호: " + code;
            String body = "MapTrips 이메일 인증을 진행합니다. 인증번호는 \"" + code + "\" 입니다.";

            smtpUtils.sendEmail(email, subject, body);

            // 인증번호를 Redis에 저장
            redisUtils.setValues(AUTH_CODE_PREFIX + email, code, Duration.ofMillis(5 * 60 * 1000));
            return true;
        }
        return false;
    }

    /**
     * 인증 코드 6자리를 생성합니다.
     * @return 6자리 숫자 문자열
     *
     * @since 2024-10-18 v1.0.0
     * @author JiwonJeong
     */
    private String createCode() {
        int legnth = 6;
        try {
            SecureRandom random = SecureRandom.getInstanceStrong();
            StringBuilder codeBuilder = new StringBuilder();

            for (int i = 0; i < legnth; i++) {
                codeBuilder.append(random.nextInt(10));
            }
            return codeBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 이메일 인증 코드를 검증합니다.
     * @param verification 검증 결과 여부 및 이메일 주소와 사용자가 입력한 인증 번호를 저장하는 객체
     * @return verification의 result - 인증 결과, message - 메시지를 저장하여 반환
     *
     * @since 2024-10-18 v1.0.0
     * @author JiwonJeong
     */
    @Override
    public Verification verifiedCode(Verification verification) {
        String redisAuthCode = redisUtils.getValues(AUTH_CODE_PREFIX + verification.getEmail());

        boolean timeout = redisUtils.checkExistsValue(redisAuthCode);
        boolean result = timeout && redisAuthCode.equals(verification.getCode());

        if (!timeout) {
            return Verification.builder().result(false).message("인증 시간이 초과되었습니다. 다시 시도하세요.").build();
        } else if (!result){
            return Verification.builder().result(false).message("인증 코드가 일치하지 않습니다. 다시 시도하세요.").build();
        } else {
            // 인증 완료 시, Redis에서 제거
            redisUtils.deleteValues(AUTH_CODE_PREFIX + verification.getEmail());
            return Verification.builder().result(true).message("인증되었습니다.").build();
        }
    }

    /**
     * username의 중복을 확인합니다.
     * @param username 입력한 username
     * @return 사용가능 여부 true, false
     *
     * @since 2024-10-20 v1.0.0
     * @author JiwonJeong
     */
    @Override
    public boolean isAvailableUsername(String username) {
        return usersRepository.findByUsername(username).isEmpty();
    }

    /**
     * 휴대전화 번호의 중복을 확인합니다.
     * @param phone 입력한 휴대전화 번호
     * @return 사용가능 여부 true, false
     *
     * @since 2024-10-20 v1.0.0
     * @author JiwonJeong
     */
    @Override
    public boolean isAvailablePhone(String phone) {
        return usersRepository.findByPhone(phone).isEmpty();
    }

    /**
     * Naver Login API를 통해 받아온 ID값을 가지는 User 객체를 가져옵니다.
     * @param naverId API를 통해 가져온 ID
     * @return Optional 로 wrapping 한 UsersDTO 객체
     *
     * @since 2024-10-20 v1.0.0
     * @author JiwonJeong
     */
    @Override
    public Optional<UsersDTO> readByNaverId(String naverId) {
        Users users = usersRepository.findByNaverId(naverId).orElse(null);

        return users == null ? Optional.empty() : Optional.of(usersMapper.toDTO(users));
    }
}
