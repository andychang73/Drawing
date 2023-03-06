package com.planto.drawing.aggregations;

import java.util.Optional;

public interface OriginatorAggService {

    char[][] getCanvasOrThrow();

    void save(String command, char[][] canvas);

    Optional<char[][]> undo();

    Optional<char[][]> redo();
}
