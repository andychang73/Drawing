package com.planto.drawing.services;

import com.planto.drawing.entities.CommandHistoryEntity;

public interface CommandHistoryService {

    void add(CommandHistoryEntity entity);

    String getLastOrEmpty();

    String getLastOrThrow();
}
