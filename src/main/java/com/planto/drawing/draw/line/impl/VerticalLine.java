package com.planto.drawing.draw.line.impl;

import com.planto.drawing.draw.line.ILine;
import org.springframework.stereotype.Component;

@Component
public class VerticalLine implements ILine {
    @Override
    public char[][] drawLine(char[][] canvas, final int[] coordinates, final char symbol) {
        int x1 = coordinates[0];
        int y1 = coordinates[1];
        int y2 = coordinates[3];
        for(int row = y1; row <= y2; row++){
            canvas[row][x1] = symbol;
        }
        return canvas;
    }

    @Override
    public boolean checkLineType(int[] coordinates) {
        int x1 = coordinates[0];
        int x2 = coordinates[2];
        return x1 == x2;
    }
}
