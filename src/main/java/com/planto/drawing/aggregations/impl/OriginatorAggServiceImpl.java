package com.planto.drawing.aggregations.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planto.drawing.aggregations.OriginatorAggService;
import com.planto.drawing.entities.CommandHistoryEntity;
import com.planto.drawing.entities.RedoEntity;
import com.planto.drawing.entities.UndoEntity;
import com.planto.drawing.services.CommandHistoryService;
import com.planto.drawing.services.RedoService;
import com.planto.drawing.services.UndoService;
import com.planto.drawing.utils.Printer;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class OriginatorAggServiceImpl implements OriginatorAggService {

    private final ObjectMapper objectMapper;
    private final UndoService undoService;
    private final RedoService redoService;
    private final CommandHistoryService historyService;

    public OriginatorAggServiceImpl(ObjectMapper objectMapper, UndoService undoService, RedoService redoService, CommandHistoryService historyService) {
        this.objectMapper = objectMapper;
        this.undoService = undoService;
        this.redoService = redoService;
        this.historyService = historyService;
    }


    @SneakyThrows
    @Override
    public char[][] getCanvasOrThrow() {
        return objectMapper.readValue(historyService.getLastOrThrow(), char[][].class);
    }

    @SneakyThrows
    @Override
    public void save(@NonNull final String command, final char[][] canvas) {
        historyService.add(
                CommandHistoryEntity.builder()
                        .command(command)
                        .canvas(objectMapper.writeValueAsString(canvas))
                        .build()
        );
    }

    @SneakyThrows
    @Override
    public char[][] undo(@NonNull final String input) {
        String currentState = historyService.getLast().orElseThrow(() -> new RuntimeException("Can't undo"));

        RedoEntity redo = RedoEntity.builder()
                .canvas(currentState)
                .build();
        redoService.add(redo);

        UndoEntity undo = undoService.getLastOrThrow();
        undoService.deleteLast(undo.getId());

        CommandHistoryEntity historyEntity = CommandHistoryEntity.builder()
                .command(input)
                .canvas(undo.getCanvas())
                .build();
        historyService.add(historyEntity);

        if(undo.getCanvas().isBlank()){
            return new char[][]{};
        }
        return objectMapper.readValue(undo.getCanvas(), char[][].class);
    }
}
