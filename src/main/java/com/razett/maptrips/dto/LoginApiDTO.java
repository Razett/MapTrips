package com.razett.maptrips.dto;

import com.razett.maptrips.properties.NaverLoginProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

/**
 * 소셜 로그인 API 이용을 위한 DTO
 *
 * @since 2024-10-15 v1.0.0
 * @author jeongjiwon
 * @version 1.0.0
 */
@Component
@Getter
public class LoginApiDTO {

    /**
     * Naver Login API 인증 요청 URL
     */
    private final String naverLoginApiURL;

    @Autowired
    public LoginApiDTO(NaverLoginProperties naverLoginProperties) {
        this.naverLoginApiURL = naverLoginProperties.getRequestUrl() +
                "&client_id=" + naverLoginProperties.getClientId() +
                "&redirect_uri=" + encodeURL(naverLoginProperties.getRedirectUrl()) +
                "&state=" + encodeURL(genState());
    }

    /**
     * Naver Login API Request에 사용되는 State 값을 생성합니다.
     * @return String state.
     * @since 2024-10-15 v1.0.0
     * @author JiwonJeong
     */
    private String genState() {
        SecureRandom random = new SecureRandom();

        return new BigInteger(130, random).toString();
    }

    /**
     * URL을 UTF-8로 Encode합니다.
     * @param url Encode할 URL.
     * @return Encode된 URL String.
     * @since 2024-10-15 v1.0.0
     * @author JiwonJeong
     */
    private String encodeURL(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return url;
        }
    }

}
