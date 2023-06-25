package com.pokeswap.api.service;

import com.pokeswap.api.model.Platform;

import java.util.List;

public interface PlatformService {

    Platform createPlatform(Platform platform);

    List<Platform> getAllPlatforms();

    Platform getPlatformById(Long id);

    Platform updatePlatform(Long id, Platform platform);

    Platform deletePlatform(Long id);


}
