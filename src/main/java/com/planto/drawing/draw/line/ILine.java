package com.planto.drawing.draw.line;

public interface ILine{

    char[][] drawLine(char[][] canvas, int[] coordinates, char symbol);

    boolean checkLineType(int[] coordinates);
}
