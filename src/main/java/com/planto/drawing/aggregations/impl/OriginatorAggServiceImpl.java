package com.planto.drawing.aggregations.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planto.drawing.aggregations.OriginatorAggService;
import com.planto.drawing.entities.CommandHistoryEntity;
import com.planto.drawing.services.CommandHistoryService;
import com.planto.drawing.services.CurrentStateService;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OriginatorAggServiceImpl implements OriginatorAggService {

    private final ObjectMapper objectMapper;
    private final CurrentStateService stateService;
    private final CommandHistoryService historyService;

    public OriginatorAggServiceImpl(ObjectMapper objectMapper, CurrentStateService stateService, CommandHistoryService historyService) {
        this.objectMapper = objectMapper;
        this.stateService = stateService;
        this.historyService = historyService;
    }

    @SneakyThrows
    @Override
    public char[][] getCanvasOrThrow() {
        int prevIndex = stateService.getCurrentIndex();
        if(prevIndex <= 0){
            throw new RuntimeException("Please create canvas first");
        }
        return objectMapper.readValue(historyService.getById(prevIndex), char[][].class);
    }

    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    @Override
    public void save(@NonNull final String command, final char[][] canvas) {
        int currentIndex = stateService.getCurrentIndex() + 1;
        historyService.add(
                CommandHistoryEntity.builder()
                        .id(currentIndex)
                        .command(command)
                        .canvas(objectMapper.writeValueAsString(canvas))
                        .build()
        );
        stateService.updateCurrentIndex(currentIndex);
        if(stateService.isResetHistory()){
            historyService.clear(currentIndex);
            stateService.updateResetHistory(false);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    @Override
    public Optional<char[][]> undo() {
        int prevIndex = stateService.getCurrentIndex() - 1;
        if(prevIndex >= 0){
            stateService.updateCurrentIndex(prevIndex);
            if(!stateService.isResetHistory()){
                stateService.updateResetHistory(true);
            }
            String canvasStr = historyService.getById(prevIndex);
            if(canvasStr.isBlank()){
                return Optional.empty();
            }
            return Optional.of(objectMapper.readValue(canvasStr, char[][].class));
        }
        return Optional.empty();
    }

    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    @Override
    public Optional<char[][]> redo() {
        int size = historyService.getSize();
        int nextIndex = stateService.getCurrentIndex() + 1;
        if(size <= 0 || nextIndex >= size){
            return Optional.empty();
        }
        stateService.updateCurrentIndex(nextIndex);
        return Optional.of(objectMapper.readValue(historyService.getById(nextIndex), char[][].class));
    }
}
