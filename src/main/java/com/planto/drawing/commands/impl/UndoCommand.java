package com.planto.drawing.commands.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planto.drawing.commands.ICommand;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UndoCommand implements ICommand {

    private final UndoService undoService;
    private final RedoService redoService;
    private final ObjectMapper objectMapper;
    private final CommandHistoryService historyService;

    @Autowired
    public UndoCommand(UndoService undoService, RedoService redoService, ObjectMapper objectMapper, CommandHistoryService historyService) {
        this.undoService = undoService;
        this.redoService = redoService;
        this.objectMapper = objectMapper;
        this.historyService = historyService;
    }

    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    @Override
    public void execute(@NonNull final String[] params) {
        String currentState = historyService.getLast().orElseThrow(() -> new RuntimeException("Can't undo"));

        RedoEntity redo = RedoEntity.builder()
                .canvas(currentState)
                .build();
        redoService.add(redo);

        UndoEntity undo = undoService.getLastOrThrow();
        undoService.deleteLast(undo.getId());

        CommandHistoryEntity historyEntity = CommandHistoryEntity.builder()
                .command(getCommand().name())
                .canvas(undo.getCanvas())
                .build();
        historyService.add(historyEntity);

        if(undo.getCanvas().isBlank()){
            return;
        }
        Printer.print(objectMapper.readValue(undo.getCanvas(), char[][].class));
    }

    @Override
    public Command getCommand() {
        return Command.Z;
    }
}
