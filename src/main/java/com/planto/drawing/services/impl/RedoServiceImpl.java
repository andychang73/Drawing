package com.planto.drawing.services.impl;

import com.planto.drawing.entities.RedoEntity;
import com.planto.drawing.repositories.RedoRepository;
import com.planto.drawing.services.RedoService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

public class RedoServiceImpl implements RedoService {

    private final RedoRepository repository;

    @Autowired
    public RedoServiceImpl(RedoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(@NonNull final RedoEntity redoEntity) {
        repository.save(redoEntity);
    }
}
