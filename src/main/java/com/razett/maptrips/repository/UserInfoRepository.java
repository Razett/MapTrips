package com.razett.maptrips.repository;

import com.razett.maptrips.entity.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

}
