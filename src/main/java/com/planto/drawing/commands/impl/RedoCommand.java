package com.planto.drawing.commands.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planto.drawing.commands.AbstractCommand;
import com.planto.drawing.entities.CommandHistoryEntity;
import com.planto.drawing.entities.RedoEntity;
import com.planto.drawing.entities.UndoEntity;
import com.planto.drawing.enums.Command;
import com.planto.drawing.services.CommandHistoryService;
import com.planto.drawing.services.RedoService;
import com.planto.drawing.services.UndoService;
import com.planto.drawing.utils.Printer;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RedoCommand extends AbstractCommand {

    private final UndoService undoService;
    private final RedoService redoService;
    private final ObjectMapper objectMapper;
    private final CommandHistoryService historyService;

    public RedoCommand(UndoService undoService, RedoService redoService, ObjectMapper objectMapper, CommandHistoryService historyService) {
        this.undoService = undoService;
        this.redoService = redoService;
        this.objectMapper = objectMapper;
        this.historyService = historyService;
    }

    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    @Override
    public void execute(@NonNull final String input) {
        String currentState = historyService.getLastOrThrow();

        UndoEntity undo = UndoEntity.builder()
                .canvas(currentState)
                .build();
        undoService.add(undo);

        RedoEntity redo = redoService.getLastOrThrow();
        redoService.deleteLast(redo.getId());

        CommandHistoryEntity historyEntity = CommandHistoryEntity.builder()
                .command(getCommand().name())
                .canvas(redo.getCanvas())
                .build();
        historyService.add(historyEntity);

        Printer.print(objectMapper.readValue(redo.getCanvas(), char[][].class));
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
