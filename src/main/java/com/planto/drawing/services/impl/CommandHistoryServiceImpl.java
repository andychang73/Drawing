package com.planto.drawing.services.impl;

import com.planto.drawing.entities.CommandHistoryEntity;
import com.planto.drawing.repositories.CommandHistoryRepository;
import com.planto.drawing.services.CommandHistoryService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommandHistoryServiceImpl implements CommandHistoryService{

    private final CommandHistoryRepository repository;

    @Autowired
    public CommandHistoryServiceImpl(CommandHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(@NonNull final CommandHistoryEntity entity) {
        repository.save(entity);
    }

    @Override
    public void clear(int index) {
        repository.clear(index);
    }

    @Override
    public Optional<String> getLast() {
        return Optional.ofNullable(repository.getLast());
    }

    @Override
    public String getById(int index) {
        return repository.selectById(index);
    }

    @Override
    public int getSize() {
        return (int)repository.count();
    }
}
