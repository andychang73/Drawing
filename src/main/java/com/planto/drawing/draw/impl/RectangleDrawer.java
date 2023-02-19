package com.planto.drawing.draw.impl;

import com.planto.drawing.draw.IDraw;
import com.planto.drawing.utils.IntegerParser;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component("rectangleDrawer")
public class RectangleDrawer implements IDraw {

    @Override
    public char[][] draw(@NonNull char[][] canvas, @NonNull final String[] params) {
        int xLowerBound = 1;
        int xUpperBound = canvas[0].length - 2;
        int yLowerBound = 1;
        int yUpperBound = canvas.length - 2;
        int[] coordinates = validateParams(params, xUpperBound, xLowerBound, yUpperBound, yLowerBound);


    }

    private int[] validateParams(String[] params, int xUpperBound, int xLowerBound, int yUpperBound, int yLowerBound) {
        if(params.length != 4){
            throw new IllegalArgumentException("Invalid rectangle parameters");
        }
        int x1 = IntegerParser.parseStr(params[0]);
        int y1 = IntegerParser.parseStr(params[1]);
        int x2 = IntegerParser.parseStr(params[2]);
        int y2 = IntegerParser.parseStr(params[3]);

        if(x1 < 0 || x1 >= x2){
            throw new IllegalArgumentException("Invalid rectangle parameters");
        }
        if(y1 < 0 || y1 >= y2){
            throw new IllegalArgumentException("Invalid rectangle parameters");
        }
        if(x1 < xLowerBound || x1 > xUpperBound){
            throw new IllegalArgumentException("Line parameters must not be out of bound");
        }
        if(x2 > xUpperBound){
            throw new IllegalArgumentException("Line parameters must not be out of bound");
        }
        if(y1 < yLowerBound || y1 > yUpperBound){
            throw new IllegalArgumentException("Line parameters must not be out of bound");
        }
        if(y2 > yUpperBound){
            throw new IllegalArgumentException("Line parameters must not be out of bound");
        }
        return new int[]{x1,y1,x2,y2};
    }
}
