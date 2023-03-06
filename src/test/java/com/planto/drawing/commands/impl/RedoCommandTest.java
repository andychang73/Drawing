//package com.planto.drawing.commands.impl;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.planto.drawing.commands.AbstractCommand;
//import com.planto.drawing.entities.RedoEntity;
//import com.planto.drawing.services.CommandHistoryService;
//import com.planto.drawing.services.RedoService;
//import com.planto.drawing.services.UndoService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class RedoCommandTest {
//
//    @Mock private UndoService undoService;
//    @Mock private RedoService redoService;
//    @Mock private ObjectMapper objectMapper;
//    @Mock private CommandHistoryService historyService;
//    @Mock private AbstractCommand redoCommand;
//
//    @Before
//    public void init(){
//        redoCommand = new RedoCommand(undoService, redoService,
//                objectMapper, historyService);
//    }
//
//    @Test
//    public void testExecute_Succeed() throws JsonProcessingException {
//
//        when(redoService.getLastOrThrow()).thenReturn(getRedo());
//        when(objectMapper.readValue(anyString(), eq(char[][].class))).thenReturn(getCanvas());
//
//        redoCommand.execute(new String[]{});
//
//        verify(undoService, times(1)).add(any());
//        verify(redoService, times(1)).deleteLast(anyInt());
//        verify(historyService, times(1)).add(any());
//    }
//
//    private RedoEntity getRedo(){
//        return RedoEntity.builder()
//                .id(1)
//                .canvas(getStringCanvas())
//                .build();
//    }
//    private char[][] getCanvas(){
//        return new char[][]{
//                {'-','-','-','-','-'},
//                {'|',' ',' ',' ','|'},
//                {'|',' ',' ',' ','|'},
//                {'-','-','-','-','-'}
//        };
//    }
//    private String getStringCanvas(){
//        return "[\"-----\",\"|   |\",\"|   |\",\"-----\"]";
//    }
//}
