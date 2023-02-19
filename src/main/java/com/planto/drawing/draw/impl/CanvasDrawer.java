package com.planto.drawing.draw.impl;

import com.planto.drawing.draw.IDraw;
import com.planto.drawing.utils.IntegerParser;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("CanvasDrawer")
public class CanvasDrawer implements IDraw {
    @Override
    public char[][] draw(@NonNull char[][] canvas, @NonNull final String[] params) {
        int[] heightAndWidth = validateParams(params);
        int numOfRows = heightAndWidth[1];
        int numOfCols = heightAndWidth[0];
        canvas = new char[numOfRows + 2][numOfCols + 2];

        numOfRows = canvas.length-2;
        numOfCols = canvas[0].length;

        initCanvas(canvas);
        createTopBottom(canvas, 0, numOfCols);
        createTopBottom(canvas, numOfRows + 1, numOfCols);
        createLeftRight(canvas, 0, numOfRows);
        createLeftRight(canvas, numOfCols - 1, numOfRows);

        return canvas;
    }

    private int[] validateParams(String[] params){
        if(params.length != 2){
            throw new IllegalArgumentException("Invalid Canvas parameters");
        }
        int height = IntegerParser.parseStr(params[0]);
        if(height < 0){
            throw new IllegalArgumentException("Invalid Canvas parameters");
        }
        int width = IntegerParser.parseStr(params[1]);
        if(width < 0){
            throw new IllegalArgumentException("Invalid Canvas parameters");
        }
        return new int[]{height, width};
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
