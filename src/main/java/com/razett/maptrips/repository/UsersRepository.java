package com.razett.maptrips.repository;

import com.razett.maptrips.entity.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

    Optional<Users> findByUsername(String username);

    Optional<Users> findByNaverId(String naverId);

    Optional<Users> findByPhone(String phone);
}
