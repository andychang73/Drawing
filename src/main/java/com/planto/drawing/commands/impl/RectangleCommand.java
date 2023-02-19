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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RectangleCommand implements ICommand {

    private final IDraw rectangleDrawer;
    private final ObjectMapper objectMapper;
    private final UndoService undoService;
    private final CommandHistoryService historyService;

    public RectangleCommand(@Qualifier("rectangleDrawer") IDraw rectangleDrawer, ObjectMapper objectMapper,
                            UndoService undoService, CommandHistoryService historyService) {
        this.rectangleDrawer = rectangleDrawer;
        this.objectMapper = objectMapper;
        this.undoService = undoService;
        this.historyService = historyService;
    }

    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    @Override
    public void execute(@NonNull final String[] params) {
        String prevState = historyService.getLastOrThrow();
        UndoEntity undo = UndoEntity.builder()
                .canvas(prevState)
                .build();
        undoService.add(undo);

        char[][] canvas = objectMapper.readValue(prevState, char[][].class);
        canvas = rectangleDrawer.draw(canvas, params);

        CommandHistoryEntity historyEntity = CommandHistoryEntity.builder()
                .command(getCommand(params))
                .canvas(objectMapper.writeValueAsString(canvas))
                .build();
        historyService.add(historyEntity);

        Printer.print(canvas);
    }

    @Override
    public Command getCommand() {
        return Command.R;
    }

    private String getCommand(String[] params){
        return this.getCommand().name() + " " + String.join(" ", params);
    }
}
