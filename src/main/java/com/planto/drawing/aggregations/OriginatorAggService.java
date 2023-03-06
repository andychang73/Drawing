package com.planto.drawing.aggregations;

public interface OriginatorAggService {

    char[][] getCanvasOrThrow();

    void save(String command, char[][] canvas);

    char[][] undo(String command);
}
