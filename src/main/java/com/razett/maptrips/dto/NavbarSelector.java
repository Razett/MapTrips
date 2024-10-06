package com.razett.maptrips.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>네비게이션 바</b>
 *
 * 네비게이션 바 벡터의 selected class 적용을 위해 사용합니다. selected일 시, 각 필드를 true로 변경하세요.
 * @since 2024-10-06
 * @author JiwonJeong
 * @version 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NavbarSelector {

    /**
     * 홈 (피드)
     */
    @Builder.Default
    private boolean home = false;

    /**
     * 검색
     */
    @Builder.Default
    private boolean search = false;

    /**
     * 글 작성
     */
    @Builder.Default
    private boolean add = false;

    /**
     * 폴더
     */
    @Builder.Default
    private boolean folder = false;

    /**
     * 마이페이지
     */
    @Builder.Default
    private boolean mypage = false;
}
