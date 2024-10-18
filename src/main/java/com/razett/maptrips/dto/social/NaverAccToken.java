package com.razett.maptrips.dto.social;

import lombok.Data;

/**
 * Naver Login API Access Token 요청 후 응답을 저장할 DTO.
 *
 * @since 2024-10-18 v1.0.0
 * @author jeongjiwon
 * @version 1.0.0
 */
@Data
public class NaverAccToken {
    private String error;
    private String error_description;
    private String access_token;
    private String refresh_token;
    private String token_type;
    private String expires_in;
}
