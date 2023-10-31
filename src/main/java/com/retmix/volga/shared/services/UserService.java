package com.retmix.volga.shared.services;

import com.retmix.volga.shared.dto.*;
import com.retmix.volga.shared.models.User;

import java.security.NoSuchAlgorithmException;

public interface UserService {
    EnterDTO signUp(UpsertUserDTO data) throws NoSuchAlgorithmException;

    MessageDTO logout(User user);

    UserDTO show(User user);

    UserDTO update(UpsertUserDTO data, User user);

    EnterDTO login(LoginDTO data) throws NoSuchAlgorithmException;

    User getUserByToken(String token);

    ChangeBalanceDTO changeBalance(Long id, User user);

}
