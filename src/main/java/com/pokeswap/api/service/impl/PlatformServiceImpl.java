package com.pokeswap.api.service.impl;

import com.pokeswap.api.model.Platform;
import com.pokeswap.api.repository.PlatformRepository;
import com.pokeswap.api.service.PlatformService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformServiceImpl implements PlatformService {

    private final PlatformRepository platformRepository;

    @Autowired
    public PlatformServiceImpl(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    @Override
    public Platform createPlatform(Platform platform) {
        return platformRepository.save(platform);
    }

    @Override
    public List<Platform> getAllPlatforms() {
        return platformRepository.findAll();
    }

    @Override
    public Platform getPlatformById(Long id) {
        return platformRepository.findById(id).orElse(null);
    }

    @Override
    public Platform updatePlatform(Long id, Platform platform) {
        Platform platformToUpdate = platformRepository.findById(id).orElse(null);
        if (platformToUpdate != null) {
            BeanUtils.copyProperties(platform, platformToUpdate, "id");
            return platformRepository.save(platformToUpdate);
        }
        return null;
    }

    @Override
    public Platform deletePlatform(Long id) {
        platformRepository.updatePlatformStatus(id, false);
        return platformRepository.findById(id).orElse(null);
    }

}
