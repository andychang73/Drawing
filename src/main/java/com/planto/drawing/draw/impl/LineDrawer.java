package com.planto.drawing.draw.impl;

import com.planto.drawing.draw.IShapeDrawer;
import com.planto.drawing.draw.line.ILine;
import com.planto.drawing.enums.Shape;
import com.planto.drawing.services.SymbolService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LineDrawer implements IShapeDrawer {

    private final List<ILine> lines;
    private final SymbolService symbolService;

    @Autowired
    public LineDrawer(List<ILine> lines, SymbolService symbolService) {
        this.lines = lines;
        this.symbolService = symbolService;
    }

    @Override
    public char[][] draw(@NonNull char[][] canvas, final int[] coordinates) {
        return lines.stream()
                .filter(l -> l.checkLineType(coordinates))
                .findFirst()
                .map(l -> l.drawLine(canvas, coordinates, symbolService.getSymbol()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid Line Type parameters"));
    }

    @Override
    public boolean validate(final char[][] canvas, final int[] params) {
        int x1 = params[0];
        int y1 = params[1];
        int x2 = params[2];
        int y2 = params[3];

        int xLowerBound = 1;
        int xUpperBound = canvas[0].length - 2;
        int yLowerBound = 1;
        int yUpperBound = canvas.length - 2;

        if(x1 < xLowerBound || x1 > xUpperBound){
            return false;
        }
        if(x2 < xLowerBound || x2 > xUpperBound){
            return false;
        }
        if(y1 < yLowerBound || y1 > yUpperBound){
            return false;
        }
        if(y2 < yLowerBound || y2 > yUpperBound){
            return false;
        }
        return true;
    }

    @Override
    public Shape getShape() {
        return Shape.LINE;
    }

}
