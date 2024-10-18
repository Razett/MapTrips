package com.razett.maptrips.service.api;

import com.razett.maptrips.dto.UsersDTO;
import com.razett.maptrips.dto.social.NaverAccToken;
import com.razett.maptrips.dto.social.NaverCode;
import com.razett.maptrips.dto.social.NaverProfile;

/**
 * Login HTML에 필요한 기능을 정의한 Interface.
 *
 * @since 2024-10-15 v1.0.0
 * @author JiwonJeong
 * @version 1.0.0
 */
public interface SocialLoginService {

    String getNaverAuthorizationUrl();

    NaverAccToken getNaverAccessToken(NaverCode naverCode);

    NaverProfile getNaverProfile(NaverAccToken naverAccToken);

    UsersDTO loginByNaver(NaverCode naverCode);
}
