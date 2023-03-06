package com.planto.drawing.commands.impl;

import com.planto.drawing.aggregations.OriginatorAggService;
import com.planto.drawing.commands.AbstractCommand;
import com.planto.drawing.enums.Command;
import com.planto.drawing.utils.Printer;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UndoCommand extends AbstractCommand {

    private final OriginatorAggService originatorAggService;

    @Autowired
    public UndoCommand(OriginatorAggService originatorAggService) {
        this.originatorAggService = originatorAggService;

    }

    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    @Override
    public void execute(@NonNull final String input) {
//        Printer.print(
//                originatorAggService.undo().orElseThrow(() -> new RuntimeException("Can't undo"))
//        );
        originatorAggService.undo().ifPresent(Printer::print);
//        String currentState = historyService.getLast().orElseThrow(() -> new RuntimeException("Can't undo"));
//
//        RedoEntity redo = RedoEntity.builder()
//                .canvas(currentState)
//                .build();
//        redoService.add(redo);
//
//        UndoEntity undo = undoService.getLastOrThrow();
//        undoService.deleteLast(undo.getId());
//
//        CommandHistoryEntity historyEntity = CommandHistoryEntity.builder()
//                .command(getCommand().name())
//                .canvas(undo.getCanvas())
//                .build();
//        historyService.add(historyEntity);
//
//        if(undo.getCanvas().isBlank()){
//            return;
//        }
//        Printer.print(objectMapper.readValue(undo.getCanvas(), char[][].class));
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
