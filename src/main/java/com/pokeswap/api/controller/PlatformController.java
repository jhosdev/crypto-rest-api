package com.pokeswap.api.controller;

import com.pokeswap.api.model.Platform;
import com.pokeswap.api.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokeswap/v1")
public class PlatformController {

    private final PlatformService platformService;

    @Autowired
    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    //URl: http://localhost:8080/api/pokeswap/v1/platforms
    //Method: GET
    //Description: Get all platforms
    //Response: List of platforms
    @Transactional(readOnly = true)
    @GetMapping("/platforms")
    public ResponseEntity<List<Platform>> getAllPlatforms() {
        return new ResponseEntity<List<Platform>>(platformService.getAllPlatforms(), HttpStatus.OK);
    }

    //URl: http://localhost:8080/api/pokeswap/v1/platforms/{id}
    //Method: GET
    //Description: Get platform by id
    //Response: Platform
    @Transactional(readOnly = true)
    @GetMapping("/platforms/{id}")
    public ResponseEntity<Platform> getPlatformById(@PathVariable Long id) {
        return new ResponseEntity<Platform>(platformService.getPlatformById(id), HttpStatus.OK);
    }

    //URl: http://localhost:8080/api/pokeswap/v1/platforms
    //Method: POST
    //Description: Create platform
    //Response: Platform
    @Transactional
    @PostMapping("/platforms")
    public ResponseEntity<Platform> createPlatform(@RequestBody Platform platform) {
        return new ResponseEntity<Platform>(platformService.createPlatform(platform), HttpStatus.CREATED);
    }

    //URl: http://localhost:8080/api/pokeswap/v1/platforms/{id}
    //Method: PUT
    //Description: Update platform
    //Response: Platform
    @Transactional
    @PutMapping("/platforms/{id}")
    public ResponseEntity<Platform> updatePlatform(@PathVariable Long id, @RequestBody Platform platform) {
        return new ResponseEntity<Platform>(platformService.updatePlatform(id, platform), HttpStatus.OK);
    }

    //URl: http://localhost:8080/api/pokeswap/v1/platforms/{id}
    //Method: DELETE
    //Description: Delete platform
    //Response: Platform
    @Transactional
    @DeleteMapping("/platforms/{id}")
    public ResponseEntity<Platform> deletePlatform(@PathVariable Long id) {
        return new ResponseEntity<Platform>(platformService.deletePlatform(id), HttpStatus.OK);
    }

}
