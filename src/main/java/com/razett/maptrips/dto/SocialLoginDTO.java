package com.razett.maptrips.dto;

import lombok.Data;

/**
 * 소셜 로그인 API 이용을 위한 DTO
 *
 * @since 2024-10-15 v1.0.0
 * @author jeongjiwon
 * @version 1.0.0
 */
@Data
public class SocialLoginDTO {

    private String code;

    private String state;

    private String error;

    private String error_description;

    private String access_token;

    private String refresh_token;

    private String token_type;

    private String expires_in;

    private String resultcode;

    private String message;

    private NaverProfileDTO response;

}
