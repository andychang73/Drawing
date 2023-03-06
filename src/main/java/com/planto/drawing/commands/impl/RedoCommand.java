package com.planto.drawing.commands.impl;

import com.planto.drawing.aggregations.OriginatorAggService;
import com.planto.drawing.commands.AbstractCommand;
import com.planto.drawing.enums.Command;
import com.planto.drawing.utils.Printer;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RedoCommand extends AbstractCommand {

    private final OriginatorAggService originatorAggService;

    public RedoCommand(OriginatorAggService originatorAggService) {
        this.originatorAggService = originatorAggService;
    }

    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    @Override
    public void execute(@NonNull final String input) {
//        Printer.print(
//                originatorAggService.redo().orElseThrow(() -> new RuntimeException("Can't redo"))
//        );
        originatorAggService.redo().ifPresent(Printer::print);
//        String currentState = historyService.getLastOrThrow();
//
//        UndoEntity undo = UndoEntity.builder()
//                .canvas(currentState)
//                .build();
//        undoService.add(undo);
//
//        RedoEntity redo = redoService.getLastOrThrow();
//        redoService.deleteLast(redo.getId());
//
//        CommandHistoryEntity historyEntity = CommandHistoryEntity.builder()
//                .command(getCommand().name())
//                .canvas(redo.getCanvas())
//                .build();
//        historyService.add(historyEntity);
//
//        Printer.print(objectMapper.readValue(redo.getCanvas(), char[][].class));
    }

    @Override
    public boolean validateInput(@NonNull final String input) {
        int[] params = parseParams(input);
        if(params.length != 0){
            throw new IllegalArgumentException("Invalid Redo parameters");
        }
        return true;
    }

    @Override
    public Command getCommand() {
        return Command.X;
    }
}
