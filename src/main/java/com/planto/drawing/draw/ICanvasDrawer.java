package com.planto.drawing.draw;

import com.planto.drawing.enums.CanvasType;

public interface ICanvasDrawer {

    char[][] drawCanvas(int[] params);

    CanvasType getCanvasType();
}
