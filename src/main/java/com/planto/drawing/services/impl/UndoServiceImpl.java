package com.planto.drawing.services.impl;

import com.planto.drawing.entities.UndoEntity;
import com.planto.drawing.repositories.UndoRepository;
import com.planto.drawing.services.UndoService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UndoServiceImpl implements UndoService {

    private final UndoRepository undoRepository;

    public UndoServiceImpl(UndoRepository undoRepository) {
        this.undoRepository = undoRepository;
    }

    @Override
    public void add(@NonNull UndoEntity entity) {
        undoRepository.save(entity);
    }
}
