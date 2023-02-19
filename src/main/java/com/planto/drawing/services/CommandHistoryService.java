package com.planto.drawing.services;

import com.planto.drawing.entities.CommandHistoryEntity;

import java.util.Optional;

public interface CommandHistoryService {

    void add(CommandHistoryEntity entity);

    String getLastOrEmpty();

    String getLastOrThrow();

    Optional<String> getLast();
}
