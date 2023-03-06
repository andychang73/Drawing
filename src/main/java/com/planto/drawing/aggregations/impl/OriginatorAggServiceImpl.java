package com.planto.drawing.aggregations.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planto.drawing.aggregations.OriginatorAggService;
import com.planto.drawing.entities.CommandHistoryEntity;
import com.planto.drawing.services.CommandHistoryService;
import com.planto.drawing.services.RedoService;
import com.planto.drawing.services.UndoService;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OriginatorAggServiceImpl implements OriginatorAggService {

    private static boolean resetHistory = false;
    private static int id = 0;
    private static int size = 0;
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
                        .id(++id)
                        .command(command)
                        .canvas(objectMapper.writeValueAsString(canvas))
                        .build()
        );
        size++;
    }

    @SneakyThrows
    @Override
    public Optional<char[][]> undo() {
        if(id > 1){
            return Optional.of(objectMapper.readValue(historyService.getById(--id), char[][].class));
        }
        return Optional.empty();
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
//                .command(input)
//                .canvas(undo.getCanvas())
//                .build();
//        historyService.add(historyEntity);
//
//        if(!resetHistory){
//            resetHistory = true;
//        }
//
//        if(undo.getCanvas().isBlank()){
//            return Optional.empty();
//        }
//        return Optional.of(objectMapper.readValue(undo.getCanvas(), char[][].class));
    }

    @SneakyThrows
    @Override
    public Optional<char[][]> redo() {
        if(size == 0 || id >= size){
            return Optional.empty();
        }
        return Optional.of(objectMapper.readValue(historyService.getById(++id), char[][].class));
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
//                .command(input)
//                .canvas(redo.getCanvas())
//                .build();
//        historyService.add(historyEntity);
//
//        return objectMapper.readValue(redo.getCanvas(), char[][].class);
    }
}
