package com.planto.drawing.services;

import com.planto.drawing.entities.SymbolEntity;

public interface SymbolService {

    char getSymbol();

    void add(SymbolEntity entity);
}
