package com.razett.maptrips.dto;

import com.razett.maptrips.entity.UserInfo;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UsersDTO
 *
 * @since 2024-10-18 v1.0.0
 * @author JiwonJeong
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersDTO {

    private Long id;

    private String email;

    private String username;

    private String password;

    private String phone;

    private String naverId;

    private UserInfoDTO userInfoDTO;
}
