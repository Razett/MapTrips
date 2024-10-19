package com.razett.maptrips.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 중복, 유효성 검사를 저장할 DTO
 *
 * @since 2024-10-20 v1.0.0
 * @author JiwonJeong
 * @version 1.0.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Verification {

    @Builder.Default
    private boolean result = false;

    private String message;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String code;
}
