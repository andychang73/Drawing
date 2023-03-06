package com.planto.drawing.services.impl;

import com.planto.drawing.entities.SymbolEntity;
import com.planto.drawing.repositories.SymbolRepository;
import com.planto.drawing.services.SymbolService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SymbolServiceImpl implements SymbolService {

    private final SymbolRepository symbolRepository;

    @Autowired
    public SymbolServiceImpl(SymbolRepository symbolRepository) {
        this.symbolRepository = symbolRepository;
    }

    @Override
    public char getSymbol() {
        return symbolRepository.getSymbol().charAt(0);
    }

    @Override
    public void add(@NonNull final SymbolEntity entity) {
        symbolRepository.save(entity);
    }
}
