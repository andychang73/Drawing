package com.planto.drawing.services.impl;

import com.planto.drawing.entities.UndoEntity;
import com.planto.drawing.repositories.UndoRepository;
import com.planto.drawing.services.UndoService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

// todo to be delete
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

    @Override
    public UndoEntity getLastOrThrow() {
        return Optional.ofNullable(undoRepository.getLast()).orElseThrow(() -> new RuntimeException("Can't undo further"));
    }

    @Override
    public void deleteLast(@NonNull final Integer id) {
        undoRepository.deleteLast(id);
    }
}
