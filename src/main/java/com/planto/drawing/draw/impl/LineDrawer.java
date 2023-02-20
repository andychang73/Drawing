package com.planto.drawing.draw.impl;

import com.planto.drawing.draw.IDraw;
import com.planto.drawing.draw.line.ILine;
import com.planto.drawing.services.SymbolService;
import com.planto.drawing.utils.IntegerParser;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("lineDrawer")
public class LineDrawer implements IDraw {

    private final List<ILine> lines;
    private final SymbolService symbolService;

    @Autowired
    public LineDrawer(List<ILine> lines, SymbolService symbolService) {
        this.lines = lines;
        this.symbolService = symbolService;
    }

    @Override
    public char[][] draw(@NonNull char[][] canvas, @NonNull final String[] params) {
        int xLowerBound = 1;
        int xUpperBound = canvas[0].length - 2;
        int yLowerBound = 1;
        int yUpperBound = canvas.length - 2;
        int[] coordinates = validateParams(params, xUpperBound, xLowerBound, yUpperBound, yLowerBound);

        return lines.stream()
                .filter(l -> l.checkLineType(coordinates))
                .findFirst()
                .map(l -> l.drawLine(canvas, coordinates, symbolService.getSymbol()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid Line Type parameters"));
    }

    private int[] validateParams(String[] params, int xUpperBound, int xLowerBound, int yUpperBound, int yLowerBound) {
        if(params.length != 4){
            throw new IllegalArgumentException("Invalid line parameters");
        }
        int x1 = IntegerParser.parseStr(params[0]);
        int y1 = IntegerParser.parseStr(params[1]);
        int x2 = IntegerParser.parseStr(params[2]);
        int y2 = IntegerParser.parseStr(params[3]);

        if(x1 < xLowerBound || x1 > xUpperBound){
            throw new IllegalArgumentException("Line parameters must not be out of bound");
        }
        if(x2 < xLowerBound || x2 > xUpperBound){
            throw new IllegalArgumentException("Line parameters must not be out of bound");
        }
        if(y1 < yLowerBound || y1 > yUpperBound){
            throw new IllegalArgumentException("Line parameters must not be out of bound");
        }
        if(y2 < yLowerBound || y2 > yUpperBound){
            throw new IllegalArgumentException("Line parameters must not be out of bound");
        }
        return new int[]{x1,y1,x2,y2};
    }
}
