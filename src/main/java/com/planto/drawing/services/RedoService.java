package com.planto.drawing.services;

import com.planto.drawing.entities.RedoEntity;

public interface RedoService {

    void add(RedoEntity redoEntity);

    RedoEntity getLastOrThrow();

    void deleteLast(int id);

    void deleteAll();
}
