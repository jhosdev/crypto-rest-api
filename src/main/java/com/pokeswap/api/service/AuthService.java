package com.pokeswap.api.service;

import com.pokeswap.api.dto.LoginDTO;
import com.pokeswap.api.dto.LoginResponseDTO;
import com.pokeswap.api.model.User;

public interface AuthService {

    LoginResponseDTO login(LoginDTO loginDTO);

    String register(User user);

}
