package com.pokeswap.api.service;

import com.pokeswap.api.dto.LoginDTO;
import com.pokeswap.api.dto.UserDTO;

public interface AuthService {

    String login(LoginDTO loginDTO);

    Boolean userExists(String email);

    Boolean register(UserDTO user);

}
