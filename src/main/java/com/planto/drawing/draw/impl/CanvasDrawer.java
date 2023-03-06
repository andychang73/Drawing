package com.planto.drawing.draw.impl;

import com.planto.drawing.draw.ICanvasDrawer;
import com.planto.drawing.draw.IShapeDrawer;
import com.planto.drawing.draw.canvas.ICanvas;
import com.planto.drawing.enums.CanvasType;
import com.planto.drawing.enums.Shape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CanvasDrawer implements ICanvasDrawer {

    private final Map<CanvasType, ICanvas> canvasFactory;
    @Autowired
    public CanvasDrawer(List<ICanvas> canvasDrawers) {
        canvasFactory = Collections.unmodifiableMap(
                canvasDrawers.stream()
                        .collect(Collectors.toMap(ICanvas::getCanvasType, v -> v))
        );
    }

    @Override
    public char[][] drawCanvas(final int[] params) {
        return Optional.ofNullable(canvasFactory.get(this.getCanvasType()))
                .orElseThrow(() -> new RuntimeException("Invalid Canvas Type"))
                .drawCanvas(params);
    }

    @Override
    public CanvasType getCanvasType() {
        return CanvasType.RECTANGULAR;
    }
}
