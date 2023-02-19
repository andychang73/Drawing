package com.planto.drawing.commands.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planto.drawing.commands.ICommand;
import com.planto.drawing.enums.Command;
import com.planto.drawing.services.CommandHistoryService;
import com.planto.drawing.services.RedoService;
import com.planto.drawing.services.UndoService;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class RedoCommand implements ICommand {

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

    @Override
    public void execute(@NonNull final String[] params) {

    }

    @Override
    public Command getCommand() {
        return Command.X;
    }
}
