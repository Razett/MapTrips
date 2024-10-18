package com.razett.maptrips.repository;

import com.razett.maptrips.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
