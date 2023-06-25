package com.pokeswap.api.controller;

import com.pokeswap.api.model.Operation;
import com.pokeswap.api.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokeswap/v1")
public class OperationController {

    private final OperationService operationService;

    @Autowired
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping("/operations")
    public ResponseEntity<Operation> createOperation(@RequestBody Operation operation) {
        return new ResponseEntity<>(operationService.createOperation(operation), HttpStatus.CREATED);
    }

    @GetMapping("/operations")
    public ResponseEntity<List<Operation>> getAllOperations() {
        return new ResponseEntity<>(operationService.getAllOperations(), HttpStatus.OK);
    }

    @GetMapping("/operations/{id}")
    public ResponseEntity<Operation> getOperationById(@PathVariable Long id) {
        return new ResponseEntity<>(operationService.getOperationById(id), HttpStatus.OK);
    }

    @PutMapping("/operations/{id}")
    public ResponseEntity<Operation> updateOperation(@PathVariable Long id, @RequestBody Operation operation) {
        return new ResponseEntity<>(operationService.updateOperation(id, operation), HttpStatus.OK);
    }

    @DeleteMapping("/operations/{id}")
    public ResponseEntity<Operation> deleteOperation(@PathVariable Long id) {
        return new ResponseEntity<>(operationService.deleteOperation(id), HttpStatus.OK);
    }
}
