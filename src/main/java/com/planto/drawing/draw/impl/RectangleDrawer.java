package com.planto.drawing.draw.impl;

import com.planto.drawing.draw.IShapeDrawer;
import com.planto.drawing.draw.rectangle.IRectangle;
import com.planto.drawing.enums.Shape;
import com.planto.drawing.services.SymbolService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RectangleDrawer implements IShapeDrawer {

    private final List<IRectangle> rectangles;
    private final SymbolService symbolService;

    @Autowired
    public RectangleDrawer(List<IRectangle> rectangles, SymbolService symbolService) {
        this.rectangles = rectangles;
        this.symbolService = symbolService;
    }

    @Override
    public char[][] draw(@NonNull char[][] canvas, final int[] params) {
        return rectangles.stream()
                .filter(r -> r.checkRectangleType(params))
                .findFirst()
                .map(r -> r.drawRectangle(canvas, params, symbolService.getSymbol()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid Rectangle Type parameters"));
    }

    @Override
    public boolean validate(char[][] canvas, int[] params) {
        int x1 = params[0];
        int y1 = params[1];
        int x2 = params[2];
        int y2 = params[3];

        int xLowerBound = 1;
        int xUpperBound = canvas[0].length - 2;
        int yLowerBound = 1;
        int yUpperBound = canvas.length - 2;

        if(x1 >= x2){
            throw new IllegalArgumentException("Invalid rectangle parameters");
        }
        if(y1 >= y2){
            throw new IllegalArgumentException("Invalid rectangle parameters");
        }
        if(x1 < xLowerBound || x1 > xUpperBound){
            return false;
        }
        if(x2 > xUpperBound){
            return false;
        }
        if(y1 < yLowerBound || y1 > yUpperBound){
            return false;
        }
        if(y2 > yUpperBound){
            return false;
        }
        return true;
    }

    @Override
    public Shape getShape() {
        return Shape.RECTANGLE;
    }
}
