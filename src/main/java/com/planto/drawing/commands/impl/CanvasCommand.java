package com.planto.drawing.commands.impl;

import com.planto.drawing.aggregations.OriginatorAggService;
import com.planto.drawing.commands.AbstractCommand;
import com.planto.drawing.enums.CanvasType;
import com.planto.drawing.enums.Command;
import com.planto.drawing.factories.CanvasFactoryService;
import com.planto.drawing.utils.Printer;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CanvasCommand extends AbstractCommand {
    private final OriginatorAggService originatorAggService;
    private final CanvasFactoryService canvasFactoryService;


    @Autowired
    public CanvasCommand(OriginatorAggService originatorAggService, CanvasFactoryService canvasFactoryService) {
        this.originatorAggService = originatorAggService;
        this.canvasFactoryService = canvasFactoryService;
    }

    @SneakyThrows
    @Override
    public void execute(@NonNull final String input) {

        char[][] canvas = canvasFactoryService.create(CanvasType.RECTANGULAR, parseParams(input));

        originatorAggService.save(input, canvas);

        Printer.print(canvas);
    }

    @Override
    public boolean validateInput(@NonNull final String input) {
        int[] params = parseParams(input);
        validateParams(params);
        if(params.length != 2){
            throw new IllegalArgumentException("Invalid Canvas parameters");
        }
        return true;
    }

    @Override
    public Command getCommand() {
        return Command.C;
    }

}
