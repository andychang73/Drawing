package com.planto.drawing.draw.rectangle;

public interface IRectangle {

    char[][] drawRectangle(char[][] canvas, int[] coordinates, char symbol);

    boolean checkRectangleType(int[] coordinates);

}
