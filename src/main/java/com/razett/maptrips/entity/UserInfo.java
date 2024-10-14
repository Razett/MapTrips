package com.razett.maptrips.entity;

import com.razett.maptrips.value.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

/**
 * <b>사용자 정보</b>
 *
 * <p>{@code Users users} - 내부 관리용 ID [BIGINT, PK, FK, Not Null]</p>
 * <p>{@code String firstName} - 이름 [VARCHAR, Null]</p>
 * <p>{@code String lastName} - 성 [VARCHAR, Null]</p>
 * <p>{@code Gender gender} - 성별 [INT, Null]</p>
 * <p>{@code Integer age} - 나이 [INT, Null]</p>
 * <p>{@code String postcode} - 우편번호 [VARCHAR, Null]</p>
 * <p>{@code String mainAddr} - 기본주소 [VARCHAR, Null]</p>
 * <p>{@code String subAddr} - 상세주소 [VARCHAR, Null]</p>
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
public class UserInfo extends BaseEntity {

    @Id
    @OneToOne
    private Users users;

    @Column(nullable = true, length = 20)
    private String firstName;

    @Column(nullable = true, length = 20)
    private String lastName;

    @Column(nullable = true)
    @ColumnDefault("0")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column(nullable = true)
    private Integer age;

    @Column(nullable = true)
    private String postcode;

    @Column(nullable = true)
    private String mainAddr;

    @Column(nullable = true)
    private String subAddr;
}
