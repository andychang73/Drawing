package com.planto.drawing.services.impl;

import com.planto.drawing.entities.RedoEntity;
import com.planto.drawing.repositories.RedoRepository;
import com.planto.drawing.services.RedoService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// todo to be delete
@Service
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

    @Override
    public RedoEntity getLastOrThrow() {
        return Optional.ofNullable(repository.getLast()).orElseThrow(() -> new RuntimeException("Can't redo further"));
    }

    @Override
    public void deleteLast(int id) {
        repository.deleteLast(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

}
