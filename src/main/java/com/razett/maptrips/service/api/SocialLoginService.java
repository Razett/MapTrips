package com.razett.maptrips.service.api;

import com.razett.maptrips.dto.SocialLoginDTO;

/**
 * Login HTML에 필요한 기능을 정의한 Interface.
 *
 * @since 2024-10-15 v1.0.0
 * @author JiwonJeong
 * @version 1.0.0
 */
public interface SocialLoginService {

    String getNaverAuthorizationUrl();

    SocialLoginDTO getNaverAccessToken(SocialLoginDTO socialLoginDTO);

    SocialLoginDTO getNaverProfile(SocialLoginDTO socialLoginDTO);
}
