package com.razett.maptrips.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Naver Login API 사용에 필요한 값을 application.properties에서 불러옵니다.
 *
 * @since 2024-10-15 v1.0.0
 * @author JiwonJeong
 * @version 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "maptrips.api.login.naver")
@Data
public class NaverLoginProperties {

    private String clientId;
    private String clientSecret;
    private String requestUrl;
    private String redirectUrl;
}
