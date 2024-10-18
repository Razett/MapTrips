package com.razett.maptrips.mapper;

import com.razett.maptrips.dto.social.NaverResponse;
import com.razett.maptrips.dto.UsersDTO;
import com.razett.maptrips.entity.Users;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Users, UsersDTO ModelMapper
 *
 * @since 2024-10-18 v1.0.0
 * @author JiwonJeong
 * @version 1.0.0
 */
@Component
@RequiredArgsConstructor
public class UsersMapper {
    private final ModelMapper modelMapper;

    /**
     * Entity To DTO
     * @param users Entity
     * @return DTO
     */
    public UsersDTO toDTO(Users users) {
        return modelMapper.map(users, UsersDTO.class);
    }

    /**
     * DTO To Entity
     * @param usersDTO DTO
     * @return Entity
     */
    public Users toEntity(UsersDTO usersDTO) {
        return modelMapper.map(usersDTO, Users.class);
    }

    /**
     * Naver Login API 사용자 프로필 정보를 Users 객체로 맵핑합니다.
     * @param naverResponse UsersDTO로 변환할 Naver Login API 사용자 프로필 정보
     * @return UsersDTO
     *
     * @since 2024-10-18 v1.0.0
     * @author jeongjiwon
     */
    public UsersDTO naverProfileToDTO(NaverResponse naverResponse) {
        ModelMapper tempMapper = new ModelMapper();
        tempMapper.typeMap(NaverResponse.class, UsersDTO.class).addMappings(mapper -> {
            mapper.map(NaverResponse::getEmail, UsersDTO::setEmail);
            mapper.map(NaverResponse::getId, UsersDTO::setNaverId);
            mapper.<String>map(src -> {
                return naverResponse.getMobile().replace("-", "");
            }, UsersDTO::setPhone);
            mapper.map(src -> null, UsersDTO::setId);
        });
        return tempMapper.map(naverResponse, UsersDTO.class);
    }
}
