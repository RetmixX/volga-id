package com.retmix.volga.shared.services;

import com.retmix.volga.shared.dto.*;

public interface UserService {
    EnterDTO signUp(UpsertUserDTO data);

    MessageDTO logout();

    UserDTO show();

    UserDTO update(UpsertUserDTO data);

    EnterDTO login(LoginDTO data);
}
