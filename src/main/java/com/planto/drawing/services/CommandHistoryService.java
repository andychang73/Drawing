package com.planto.drawing.services;

import com.planto.drawing.entities.CommandHistoryEntity;

import java.util.Optional;

public interface CommandHistoryService {

    void add(CommandHistoryEntity entity);

    void clear(int index);

    String getLastOrThrow();

    Optional<String> getLast();

    String getById(int index);

    int getSize();
}
