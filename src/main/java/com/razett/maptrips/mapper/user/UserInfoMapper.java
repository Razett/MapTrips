package com.razett.maptrips.mapper.user;

import com.razett.maptrips.dto.social.NaverResponse;
import com.razett.maptrips.dto.user.UserInfoDTO;
import com.razett.maptrips.entity.user.UserInfo;
import com.razett.maptrips.type.Gender;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * UserInfoDTO, UserInfo ModelMapper
 *
 * @since 2024-10-18 v1.0.0
 * @author JiwonJeong
 * @version 1.0.0
 */
@Component
@RequiredArgsConstructor
public class UserInfoMapper {

    private final ModelMapper modelMapper;

    /**
     * Entity To DTO
     * @param userInfo Entity
     * @return DTO
     */
    public UserInfoDTO toDTO(UserInfo userInfo) {
        return modelMapper.map(userInfo, UserInfoDTO.class);
    }

    /**
     * DTO To Entity
     * @param userInfoDTO DTO
     * @return Entity
     */
    public UserInfo toEntity(UserInfoDTO userInfoDTO) {
        return modelMapper.map(userInfoDTO, UserInfo.class);
    }

    /**
     * Naver Login API 사용자 프로필 정보를 UserInfo 객체로 맵핑합니다.
     * UserInfo의 Birthday는 Mapper 호출 전 수동 맵핑됩니다.
     *
     * @param naverResponse UserInfo로 변환할 Naver Login API 사용자 프로필 정보
     * @return UserInfoDTO
     *
     * @since 2024-10-18 v1.0.0
     * @author jeongjiwon
     */
    public UserInfoDTO naverProfileToDTO(NaverResponse naverResponse) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();

        if (naverResponse.getBirthday() != null) {
            int year = Integer.parseInt(naverResponse.getBirthyear());
            int month = Integer.parseInt(naverResponse.getBirthday().split("-")[0]);
            int day = Integer.parseInt(naverResponse.getBirthday().split("-")[1]);
            userInfoDTO.setBirthday(LocalDate.of(year, month, day));
        } else {
            userInfoDTO.setBirthday(null); // null로 처리
        }

        ModelMapper tempMapper = new ModelMapper();

        tempMapper.typeMap(NaverResponse.class, UserInfoDTO.class).addMappings(mapper -> {
            mapper.map(NaverResponse::getName, UserInfoDTO::setFirstName);
            mapper.map(src -> {
                String genderStr = naverResponse.getGender();
                if (genderStr == null || genderStr.isEmpty()) {
                    return Gender.NONE;
                }
                switch (genderStr) {
                    case "M":
                        return Gender.MALE;
                    case "F":
                        return Gender.FEMALE;
                    default:
                        return Gender.NONE;
                }
            }, UserInfoDTO::setGender);
            mapper.map(src -> {
                int currentYear = LocalDate.now().getYear();
                int birthYear = Integer.parseInt(naverResponse.getBirthyear());
                return currentYear - birthYear + 1;
            }, UserInfoDTO::setAge);
        });

        tempMapper.map(naverResponse, userInfoDTO);
        return userInfoDTO;
    }
}
