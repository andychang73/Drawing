package com.planto.drawing.draw.line.impl;

import com.planto.drawing.draw.line.ILine;
import org.springframework.stereotype.Component;

@Component
public class HorizontalLine implements ILine {

    @Override
    public char[][] drawLine(char[][] canvas, final int[] coordinates, final char symbol) {
        int x1 = coordinates[0];
        int y1 = coordinates[1];
        int x2 = coordinates[2];
        for(int col = x1; col <= x2; col++){
            canvas[y1][col] = symbol;
        }
        return canvas;
    }

    @Override
    public boolean checkLineType(int[] coordinates) {
        int y1 = coordinates[1];
        int y2 = coordinates[3];
        return y1 == y2;
    }
}
