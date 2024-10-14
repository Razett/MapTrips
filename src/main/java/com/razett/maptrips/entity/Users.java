package com.razett.maptrips.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * <b>사용자</b>
 *
 * <p>{@code Long id} - 내부 관리용 ID [BIGINT, PK, Not Null]</p>
 * <p>{@code String email} - E-Mail [VARCHAR, Not Null]</p>
 * <p>{@code String username} - Username [VARCHAR, Not Null]</p>
 * <p>{@code String password} - 암호 [VARCHAR, Not Null]</p>
 * <p>{@code String phone} - 휴대전화 [VARCHAR, Not Null]</p>
 *
 * @since 2024-10-06 v1.0.0
 * @author JiwonJeong
 * @version 1.0.0
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 64)
    private String email;

    @Column(nullable = false, unique = true, length = 64)
    private String username;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, length = 12)
    private String phone;

}
