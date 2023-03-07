package com.planto.drawing.aggregations.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.planto.drawing.services.CommandHistoryService;
import com.planto.drawing.services.CurrentStateService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OriginatorAggServiceTest {

    @Mock private ObjectMapper objectMapper;
    @Mock private CurrentStateService currentStateService;
    @Mock private CommandHistoryService historyService;
    @Mock private OriginatorAggServiceImpl originatorAggService;

    @Before
    public void init(){
        originatorAggService = new OriginatorAggServiceImpl(objectMapper, currentStateService, historyService);
    }

    @Test(expected = RuntimeException.class)
    public void testGetCanvasOrThrow_FailedWhenPrevIndexIsLessOrEqualToZero(){
        when(currentStateService.getCurrentIndex()).thenReturn(0);

        originatorAggService.getCanvasOrThrow();
    }

    @Test
    public void testGetCanvasOrThrow_SucceedWhenPrevIndexIsGreaterThanZero() throws JsonProcessingException {
        when(currentStateService.getCurrentIndex()).thenReturn(1);
        when(historyService.getById(anyInt())).thenReturn(getStringCanvas());

        originatorAggService.getCanvasOrThrow();

        verify(objectMapper, times(1)).readValue(anyString(), eq(char[][].class));
    }

    @Test
    public void testSaveSucceed_WhenIsResetHistoryIsFalse() {

        originatorAggService.save("Command", getResult());

        verify(currentStateService, times(1)).getCurrentIndex();
        verify(historyService, times(1)).add(any());
        verify(currentStateService, times(1)).updateCurrentIndex(anyInt());
        verify(currentStateService, times(1)).isResetHistory();
        verify(historyService, never()).clear(anyInt());
        verify(currentStateService, never()).updateResetHistory(anyBoolean());
    }

    @Test
    public void testSavedSucceed_WhenIsResetHistoryIsTrue(){

        when(currentStateService.isResetHistory()).thenReturn(true);

        originatorAggService.save("Command", getResult());

        verify(currentStateService, times(1)).getCurrentIndex();
        verify(historyService, times(1)).add(any());
        verify(currentStateService, times(1)).updateCurrentIndex(anyInt());
        verify(currentStateService, times(1)).isResetHistory();
        verify(historyService, times(1)).clear(anyInt());
        verify(currentStateService, times(1)).updateResetHistory(anyBoolean());
    }

    @Test
    public void testUndoSucceed_WhenPrevIndexIsLessThanZero() throws JsonProcessingException {
        when(currentStateService.getCurrentIndex()).thenReturn(0);

        Optional<char[][]> result = originatorAggService.undo();

        verify(currentStateService, never()).updateCurrentIndex(anyInt());
        verify(currentStateService, never()).isResetHistory();
        verify(historyService, never()).getById(anyInt());
        verify(objectMapper, never()).readValue(anyString(), eq(char[][].class));
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testUndoSucceed_WhenPrevIndexIsGreaterOrEqualToZeroAndIsResetHistoryIsFalse() throws JsonProcessingException {
        when(currentStateService.getCurrentIndex()).thenReturn(1);
        when(historyService.getById(anyInt())).thenReturn(getStringCanvas());
        when(objectMapper.readValue(anyString(), eq(char[][].class))).thenReturn(getResult());

        originatorAggService.undo();

        verify(currentStateService, times(1)).updateCurrentIndex(anyInt());
        verify(currentStateService, times(1)).isResetHistory();
        verify(currentStateService, times(1)).updateResetHistory(anyBoolean());
    }

    @Test
    public void testUndoSucceed_WhenPrevIndexIsGreaterOrEqualToZeroAndIsResetHistoryIsTrue() throws JsonProcessingException {
        when(currentStateService.getCurrentIndex()).thenReturn(1);
        when(currentStateService.isResetHistory()).thenReturn(true);
        when(historyService.getById(anyInt())).thenReturn(getStringCanvas());
        when(objectMapper.readValue(anyString(), eq(char[][].class))).thenReturn(getResult());

        originatorAggService.undo();

        verify(currentStateService, times(1)).updateCurrentIndex(anyInt());
        verify(currentStateService, times(1)).isResetHistory();
        verify(currentStateService, never()).updateResetHistory(anyBoolean());
    }

    @Test
    public void testUndoSucceed_WhenCanvasStrIsBlank(){
        when(currentStateService.getCurrentIndex()).thenReturn(1);
        when(historyService.getById(anyInt())).thenReturn("");

        Optional<char[][]> result = originatorAggService.undo();

        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testUndoSucceed_WhenCanvasStrIsNotBlank() throws JsonProcessingException {
        when(currentStateService.getCurrentIndex()).thenReturn(1);
        when(historyService.getById(anyInt())).thenReturn(getStringCanvas());
        when(objectMapper.readValue(anyString(), eq(char[][].class))).thenReturn(getResult());

        Optional<char[][]> result = originatorAggService.undo();

        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void redoSucceed_WhenSizeIsLessOrEqualToZero(){
        when(historyService.getSize()).thenReturn(0);

        Optional<char[][]> result = originatorAggService.redo();

        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void redoSucceed_WhenNextIndexIsGreaterOrEqualToSize(){
        when(historyService.getSize()).thenReturn(1);
        when(currentStateService.getCurrentIndex()).thenReturn(1);

        Optional<char[][]> result = originatorAggService.redo();

        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void redoSucceed_WhenSizeIsGreaterThanZeroAndNextIndexIsLessThanSize() throws JsonProcessingException {
        when(historyService.getSize()).thenReturn(2);
        when(currentStateService.getCurrentIndex()).thenReturn(0);
        when(historyService.getById(anyInt())).thenReturn(getStringCanvas());
        when(objectMapper.readValue(anyString(), eq(char[][].class))).thenReturn(getResult());

        Optional<char[][]> result = originatorAggService.redo();

        verify(currentStateService, times(1)).updateCurrentIndex(anyInt());
        Assert.assertTrue(result.isPresent());
    }

    private char[][] getResult(){
        return new char[][]{
                {'-','-','-','-','-'},
                {'|',' ',' ',' ','|'},
                {'|',' ',' ',' ','|'},
                {'-','-','-','-','-'}
        };
    }
    private String getStringCanvas(){
        return "[\"-----\",\"|   |\",\"|   |\",\"-----\"]";
    }
}
