package com.planto.drawing.draw.canvas.impl;

import com.planto.drawing.draw.canvas.ICanvas;
import com.planto.drawing.enums.CanvasType;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RectangularCanvas implements ICanvas {

    @Override
    public char[][] drawCanvas(final int[] params) {
        int numOfRows = params[1];
        int numOfCols = params[0];
        char[][] canvas = new char[numOfRows + 2][numOfCols + 2];

        numOfRows = canvas.length-2;
        numOfCols = canvas[0].length;

        initCanvas(canvas);
        createTopBottom(canvas, 0, numOfCols);
        createTopBottom(canvas, numOfRows + 1, numOfCols);
        createLeftRight(canvas, 0, numOfRows);
        createLeftRight(canvas, numOfCols - 1, numOfRows);

        return canvas;
    }

    @Override
    public CanvasType getCanvasType() {
        return CanvasType.RECTANGULAR;
    }

    private void initCanvas(char[][] canvas){
        for(char[] arr : canvas){
            Arrays.fill(arr, ' ');
        }
    }

    private void createTopBottom(char[][] canvas, int row, int length){
        for(int i = 0; i < length; i++){
            canvas[row][i] = '-';
        }
    }

    private void createLeftRight(char[][] canvas, int col, int length){
        for(int i = 1; i < length + 1; i++){
            canvas[i][col] = '|';
        }
    }
}
