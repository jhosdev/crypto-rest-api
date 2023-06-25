package com.pokeswap.api.service;

import com.pokeswap.api.model.Operation;

import java.util.List;

public interface OperationService {
    Operation createOperation(Operation operation);

    List<Operation> getAllOperations();

    Operation getOperationById(Long id);

    Operation updateOperation(Long id, Operation operation);

    Operation deleteOperation(Long id);


}
