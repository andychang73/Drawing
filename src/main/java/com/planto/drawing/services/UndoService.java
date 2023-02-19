package com.planto.drawing.services;

import com.planto.drawing.entities.UndoEntity;

public interface UndoService {

    void add(UndoEntity entity);

    UndoEntity getLastOrThrow();

    void deleteLast(Integer id);
}
