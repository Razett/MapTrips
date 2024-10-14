package com.razett.maptrips.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <b>성별</b>
 *
 * @since 2024-10-06 v1.0.0
 * @author JiwonJeong
 * @version 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum Gender {
    NONE("선택하지 않음"),
    MALE("남성"),
    FEMALE("여성");

    private final String text;
}
