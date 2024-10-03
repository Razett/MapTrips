package com.razett.maptrips.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends BaseEntity {

    @Id
    @OneToOne
    private User user;

    @Column(nullable = true, length = 20)
    private String firstName;

    @Column(nullable = true, length = 20)
    private String lastName;

    @Column(nullable = true)
    private Integer age;

    @Column(nullable = true)
    private String postcode;

    @Column(nullable = true)
    private String mainAddr;

    @Column(nullable = true)
    private String subAddr;
}
