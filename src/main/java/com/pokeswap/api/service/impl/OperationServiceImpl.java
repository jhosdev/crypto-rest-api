package com.pokeswap.api.service.impl;

import com.pokeswap.api.model.Operation;
import com.pokeswap.api.repository.OperationRepository;
import com.pokeswap.api.service.OperationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;

    @Autowired
    public OperationServiceImpl(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public Operation createOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    @Override
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    @Override
    public Operation getOperationById(Long id) {
        return operationRepository.findById(id).orElse(null);
    }

    @Override
    public Operation updateOperation(Long id, Operation operation) {
        Operation operationToUpdate = operationRepository.findById(id).orElse(null);
        if (operationToUpdate != null) {
            BeanUtils.copyProperties(operation, operationToUpdate, "id");
            return operationRepository.save(operationToUpdate);
        }
        return null;
    }

    @Override
    public Operation deleteOperation(Long id) {
        operationRepository.updateOperationStatus(id, false);
        return operationRepository.findById(id).orElse(null);
    }


}
