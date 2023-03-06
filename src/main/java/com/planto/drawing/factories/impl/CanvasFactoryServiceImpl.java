package com.planto.drawing.factories.impl;

import com.planto.drawing.draw.canvas.ICanvas;
import com.planto.drawing.enums.CanvasType;
import com.planto.drawing.factories.CanvasFactoryService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CanvasFactoryServiceImpl implements CanvasFactoryService {

    private final Map<CanvasType, ICanvas> canvasFactory;

    @Autowired
    public CanvasFactoryServiceImpl(List<ICanvas> canvasList) {
        this.canvasFactory = Collections.unmodifiableMap(
                canvasList.stream()
                        .collect(Collectors.toMap(ICanvas::getCanvasType, v -> v))
        );
    }


    @Override
    public char[][] create(@NonNull final CanvasType canvasType, final int[] params) {
        return Optional.ofNullable(canvasFactory.get(canvasType))
                .orElseThrow(() -> new RuntimeException("Invalid Canvas Type"))
                .drawCanvas(params);
    }
}
