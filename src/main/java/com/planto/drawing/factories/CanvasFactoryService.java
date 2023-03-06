package com.planto.drawing.factories;

import com.planto.drawing.enums.CanvasType;

public interface CanvasFactoryService {

    char[][] create(CanvasType canvasType, int[] params);
}
