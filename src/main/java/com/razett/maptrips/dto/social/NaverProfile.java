package com.razett.maptrips.dto.social;

import lombok.Data;

/**
 * Naver Login API 토큰을 통해 요청한 사용자 프로필을 저장할 DTO
 *
 * @since 2024-10-17 v1.0.0
 * @author jeongjiwon
 * @version 1.0.0
 */
@Data
public class NaverProfile {
    private String resultcode;
    private String message;
    private NaverResponse response;
}
