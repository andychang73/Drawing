package com.planto.drawing.commands.impl;

import com.planto.drawing.aggregations.OriginatorAggService;
import com.planto.drawing.commands.AbstractCommand;
import com.planto.drawing.enums.Command;
import com.planto.drawing.utils.Printer;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UndoCommand extends AbstractCommand {

    private final OriginatorAggService originatorAggService;

    @Autowired
    public UndoCommand(OriginatorAggService originatorAggService) {
        this.originatorAggService = originatorAggService;

    }

    @SneakyThrows
    @Override
    public void execute(@NonNull final String input) {
        originatorAggService.undo().ifPresent(Printer::print);
    }

    @Override
    public boolean validateInput(@NonNull final String input) {
        int[] params = parseParams(input);
        if(params.length != 0){
            throw new IllegalArgumentException("Invalid Undo parameters");
        }
        return true;
    }

    @Override
    public Command getCommand() {
        return Command.Z;
    }
}
