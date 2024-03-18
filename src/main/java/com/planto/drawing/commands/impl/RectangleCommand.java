package com.planto.drawing.commands.impl;

import com.planto.drawing.aggregations.OriginatorAggService;
import com.planto.drawing.commands.AbstractCommand;
import com.planto.drawing.enums.Command;
import com.planto.drawing.enums.Shape;
import com.planto.drawing.factories.ShapeFactoryService;
import com.planto.drawing.utils.Printer;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RectangleCommand extends AbstractCommand {

    private final OriginatorAggService originatorAggService;
    private final ShapeFactoryService shapeFactoryService;

    @Autowired
    public RectangleCommand(OriginatorAggService originatorAggService, ShapeFactoryService shapeFactoryService) {
        this.originatorAggService = originatorAggService;
        this.shapeFactoryService = shapeFactoryService;
    }


    @SneakyThrows
    @Override
    public void execute(@NonNull final String input) {
        char[][] prevState = originatorAggService.getCanvasOrThrow();

        char[][] current = shapeFactoryService.draw(Shape.RECTANGLE, prevState, parseParams(input));

        originatorAggService.save(input, current);

        Printer.print(current);
    }

    @Override
    public boolean validateInput(@NonNull final String input) {
        int[] params = parseParams(input);
        validateParams(params);
        if(params.length != 4){
            throw new IllegalArgumentException("Invalid Rectangle parameters");
        }
        return true;
    }

    @Override
    public Command getCommand() {
        return Command.R;
    }


}
