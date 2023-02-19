package com.planto.drawing.commands.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planto.drawing.commands.ICommand;
import com.planto.drawing.draw.IDraw;
import com.planto.drawing.entities.CommandHistoryEntity;
import com.planto.drawing.entities.UndoEntity;
import com.planto.drawing.enums.Command;
import com.planto.drawing.services.CommandHistoryService;
import com.planto.drawing.services.UndoService;
import com.planto.drawing.utils.Printer;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CanvasCommand implements ICommand {

    private final IDraw canvasDrawer;
    private final ObjectMapper objectMapper;
    private final UndoService undoService;
    private final CommandHistoryService historyService;


    @Autowired
    public CanvasCommand(CommandHistoryService historyService, UndoService undoService,
                         @Qualifier("CanvasDrawer") IDraw canvasDrawer, ObjectMapper objectMapper) {
        this.historyService = historyService;
        this.canvasDrawer = canvasDrawer;
        this.undoService = undoService;
        this.objectMapper = objectMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    @Override
    public void execute(@NonNull final String[] params) {
        char[][] canvas = canvasDrawer.draw(new char[][]{},params);

        String prevState = historyService.getLastOrEmpty();
        UndoEntity undo = UndoEntity.builder()
                .canvas(prevState)
                .build();
        undoService.add(undo);

        CommandHistoryEntity historyEntity = CommandHistoryEntity.builder()
                .command(getCommand(params))
                .canvas(objectMapper.writeValueAsString(canvas))
                .build();
        historyService.add(historyEntity);

        Printer.print(canvas);
    }

    @Override
    public Command getCommand() {
        return Command.C;
    }

    private String getCommand(String[] params){
        return this.getCommand().name() + " "+ String.join(" ", params);
    }
}
