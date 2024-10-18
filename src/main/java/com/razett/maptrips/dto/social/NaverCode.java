package com.razett.maptrips.dto.social;

import lombok.Data;

/**
 * Naver login API 인증 완료 후 응답을 저장할 DTO.
 *
 * @since 2024-10-18 v1.0.0
 * @author jeongjiwon
 * @version 1.0.0
 */
@Data
public class NaverCode {
    private String code;
    private String state;
    private String error;
    private String error_description;
}
