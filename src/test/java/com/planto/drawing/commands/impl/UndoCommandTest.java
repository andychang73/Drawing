//package com.planto.drawing.commands.impl;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.planto.drawing.commands.AbstractCommand;
//import com.planto.drawing.entities.UndoEntity;
//import com.planto.drawing.services.CommandHistoryService;
//import com.planto.drawing.services.RedoService;
//import com.planto.drawing.services.UndoService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UndoCommandTest {
//
//    @Mock private UndoService undoService;
//    @Mock private RedoService redoService;
//    @Mock private ObjectMapper objectMapper;
//    @Mock private CommandHistoryService historyService;
//    @Mock private AbstractCommand undoCommand;
//
//    @Before
//    public void init(){
//        undoCommand = new UndoCommand(undoService, redoService,
//                objectMapper, historyService);
//    }
//
//    @Test(expected = RuntimeException.class)
//    public void testExecute_FailedWhenCannotUndoFurther(){
//        when(historyService.getLast()).thenReturn(Optional.empty());
//
//        undoCommand.execute(new String[]{});
//    }
//
//    @Test
//    public void testExecute_SucceedWhenUndoCanvasIsBlank() throws JsonProcessingException {
//        when(historyService.getLast()).thenReturn(Optional.of(getStringCanvas()));
//        when(undoService.getLastOrThrow()).thenReturn(getEmptyCanvasUndo());
//
//        undoCommand.execute(new String[]{});
//
//        verify(undoService, times(1)).deleteLast(anyInt());
//        verify(historyService, times(1)).add(any());
//        verify(objectMapper, never()).readValue(anyString(), eq(char[][].class));
//    }
//
//    @Test
//    public void testExecute_SucceedWhenUndoCanvasIsNotBlank() throws JsonProcessingException {
//        when(historyService.getLast()).thenReturn(Optional.of(getStringCanvas()));
//        when(undoService.getLastOrThrow()).thenReturn(getNonEmptyCanvasUndo());
//        when(objectMapper.readValue(anyString(), eq(char[][].class))).thenReturn(getCanvas());
//
//        undoCommand.execute(new String[]{});
//
//        verify(undoService, times(1)).deleteLast(anyInt());
//        verify(historyService, times(1)).add(any());
//    }
//
//    private String getStringCanvas(){
//        return "[\"-----\",\"|   |\",\"|   |\",\"-----\"]";
//    }
//
//    private char[][] getCanvas(){
//        return new char[][]{
//                {'-','-','-','-','-'},
//                {'|',' ',' ',' ','|'},
//                {'|',' ',' ',' ','|'},
//                {'-','-','-','-','-'}
//        };
//    }
//
//    private UndoEntity getNonEmptyCanvasUndo(){
//        return UndoEntity.builder()
//                .id(1)
//                .canvas(getStringCanvas())
//                .build();
//    }
//    private UndoEntity getEmptyCanvasUndo(){
//        return UndoEntity.builder()
//                .id(1)
//                .canvas("")
//                .build();
//    }
//}
