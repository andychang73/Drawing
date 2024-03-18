package com.planto.drawing.factories.impl;

import com.planto.drawing.draw.IShapeDrawer;
import com.planto.drawing.enums.Shape;
import com.planto.drawing.factories.ShapeFactoryService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ShapeFactoryServiceImpl implements ShapeFactoryService {

    private final Map<Shape, IShapeDrawer> shapeFactory;

    @Autowired
    public ShapeFactoryServiceImpl(List<IShapeDrawer> drawers){
        shapeFactory = Collections.unmodifiableMap(
                drawers.stream()
                        .collect(Collectors.toMap(IShapeDrawer::getShape, v -> v))
        );
    }

    @Override
    public char[][] draw(@NonNull final Shape shape, char[][] canvas, final int[] params) {
        return Optional.ofNullable(shapeFactory.get(shape))
                .filter(s -> s.validate(canvas,params))
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Out of bound"))
                .draw(canvas, params);
    }
}
