package com.razett.maptrips.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <b>공개 Type</b>
 *
 * @since 2024-10-17 v1.0.0
 * @author JiwonJeong
 * @version 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum Visibility {

    VISIBLE("공개"),
    INVISIBLE("비공개");

    private final String text;
}
