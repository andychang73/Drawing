package com.planto.drawing.draw;

import com.planto.drawing.enums.Shape;

public interface IShapeDrawer {

    char[][] draw(char[][] canvas, int[] params);

    boolean validate(char[][] canvas, int[] params);
    Shape getShape();
}
