package com.razett.maptrips.service.api;

import com.razett.maptrips.dto.UserInfoDTO;
import com.razett.maptrips.dto.UsersDTO;
import com.razett.maptrips.dto.social.NaverAccToken;
import com.razett.maptrips.dto.social.NaverCode;
import com.razett.maptrips.dto.social.NaverProfile;
import com.razett.maptrips.mapper.UserInfoMapper;
import com.razett.maptrips.mapper.UsersMapper;
import com.razett.maptrips.properties.NaverLoginProperties;
import com.razett.maptrips.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * Login HTML에 필요한 서비스를 구현한 Class
 *
 * @since 2024-10-15 v1.0.0
 * @author JiwonJeong
 * @version 1.0.0
 */

@Service
@RequiredArgsConstructor
public class SocialLoginServiceImpl implements SocialLoginService {

    private final NaverLoginProperties naverLoginProperties;
    private final UsersMapper usersMapper;
    private final UserInfoMapper userInfoMapper;

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

    /**
     * Naver Login API 인증 요청 URL을 생성합니다.
     * @return URL
     * @since 2024-10-17 v1.0.0
     * @author JiwonJeong
     */
    public String getNaverAuthorizationUrl() {
        return naverLoginProperties.getRequestUrl() +
                "&client_id=" + naverLoginProperties.getClientId() +
                "&redirect_uri=" + encodeURL(naverLoginProperties.getRedirectUrl()) +
                "&state=" + encodeURL(genState());
    }

    /**
     * Naver Login API 로그인 한 사용자 접근 토큰을 획득합니다.
     * @param naverCode 해당 객체 내 code와 state값을 사용합니다.
     * @return 요청의 결과를 새로운 {@code SocialLoginDto}로 받습니다.
     * @since 2024-10-17 v1.0.0
     * @author JiwonJeong
     */
    @Override
    public NaverAccToken getNaverAccessToken(NaverCode naverCode) {
        String apiUrl = naverLoginProperties.getAccessTokenUrl() +
                "&client_id=" + naverLoginProperties.getClientId() +
                "&client_secret=" + naverLoginProperties.getClientSecret() +
                "&redirect_uri=" + encodeURL(naverLoginProperties.getRedirectUrl()) +
                "&code=" + naverCode.getCode() +
                "&state=" + encodeURL(naverCode.getState());

        Map<String, String> requestHeaders = new HashMap<>();

        return HttpUtils.get(apiUrl, requestHeaders, NaverAccToken.class);
    }

    /**
     * Naver Login API를 통해 사용자 프로필 정보를 가져옵니다.
     * @param naverAccToken 해당 객체 내 access_token 값을 사용합니다.
     * @return NaverResponse를 포함하는 NaverProfile 객체를 반환합니다.
     * @since 2024-10-17 v1.0.0
     * @author JiwonJeong
     */
    @Override
    public NaverProfile getNaverProfile(NaverAccToken naverAccToken) {
        String apiUrl = naverLoginProperties.getQueryProfileUrl();
        String header = "Bearer " + naverAccToken.getAccess_token();

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);

        return HttpUtils.get(apiUrl, requestHeaders, NaverProfile.class);
    }

    @Override
    public UsersDTO loginByNaver(NaverCode naverCode) {
        if (naverCode.getCode() != null) {
            NaverAccToken naverAccToken = this.getNaverAccessToken(naverCode);

            if (naverAccToken.getAccess_token() != null) {
                NaverProfile naverProfile = this.getNaverProfile(naverAccToken);

                if (naverProfile.getResponse() != null) {
                    UsersDTO usersDTO = usersMapper.naverProfileToDTO(naverProfile.getResponse());
                    UserInfoDTO userInfoDTO = userInfoMapper.naverProfileToDTO(naverProfile.getResponse());

                    usersDTO.setUserInfoDTO(userInfoDTO);
                    return usersDTO;
                } else {
                    System.out.println(naverProfile.getMessage());
                }
            } else {
                System.out.println(naverAccToken.getError_description());
            }
        } else {
            System.out.println(naverCode.getError_description());
        }
        return null;
    }
}
