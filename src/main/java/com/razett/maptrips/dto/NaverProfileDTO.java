package com.razett.maptrips.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Naver Login API를 통해 전달받은 Data.
 *
 * @since 2024-10-17 v1.0.0
 * @author jeongjiwon
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class NaverProfileDTO {
    private String id;
    private String nickname;
    private String name;
    private String email;
    private String gender;
    private String age;
    private String birthday;
    private String profile_image;
    private String birthyear;
    private String mobile;
}
