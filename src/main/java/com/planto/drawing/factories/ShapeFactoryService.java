package com.planto.drawing.factories;

import com.planto.drawing.enums.Shape;

public interface ShapeFactoryService {

    char[][] draw(Shape shape, char[][] canvas, int[] params);
}
