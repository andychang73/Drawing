package com.planto.drawing.draw.canvas;

import com.planto.drawing.enums.CanvasType;

public interface ICanvas {

//    char[][] drawCanvas(char[][] canvas, int[] params);
    char[][] drawCanvas(int[] params);

    CanvasType getCanvasType();
}
