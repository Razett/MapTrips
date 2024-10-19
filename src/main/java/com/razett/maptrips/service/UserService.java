package com.razett.maptrips.service;

import com.razett.maptrips.dto.user.UsersDTO;
import com.razett.maptrips.dto.user.Verification;

import java.util.Optional;

public interface UserService {

    boolean isAvailableEmail(String email);

    boolean sendVerificationCode(String email);

    Verification verifiedCode(Verification verification);

    boolean isAvailableUsername(String username);

    boolean isAvailablePhone(String phone);

    Optional<UsersDTO> readByNaverId(String naverId);
}
