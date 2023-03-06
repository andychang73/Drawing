package com.planto.drawing.draw.rectangle.impl;

import com.planto.drawing.draw.rectangle.IRectangle;
import org.springframework.stereotype.Component;

@Component
public class Rectangle implements IRectangle {

    @Override
    public char[][] drawRectangle(char[][] canvas, int[] params, char symbol) {
        int x1 = params[0];
        int y1 = params[1];
        int x2 = params[2];
        int y2 = params[3];

        for(int i = y1; i <= y2; i++){
            for(int k = x1; k <= x2; k++){
                if(i == y1 || i == y2){
                    canvas[i][k] = symbol;
                }else if(k == x1 || k == x2){
                    canvas[i][k] = symbol;
                }
            }
        }
        return canvas;
    }

    @Override
    public boolean checkRectangleType(int[] coordinates) {
        return true;
    }
}
