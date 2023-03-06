//package com.planto.drawing.commands.impl;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.planto.drawing.aggregations.OriginatorAggService;
//import com.planto.drawing.commands.AbstractCommand;
//import com.planto.drawing.draw.IDraw;
//import com.planto.drawing.factories.DrawerFactoryService;
//import com.planto.drawing.services.CommandHistoryService;
//import com.planto.drawing.services.RedoService;
//import com.planto.drawing.services.UndoService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class CanvasCommandTest {
//
//    @Mock private IDraw canvasDrawer;
//    @Mock private ObjectMapper objectMapper;
//    @Mock private UndoService undoService;
//    @Mock private RedoService redoService;
//    @Mock private OriginatorAggService originatorAggService;
//
//    @Mock private CommandHistoryService commandHistoryService;
//    @Mock private DrawerFactoryService drawerFactoryService;
//    @Mock private AbstractCommand canvasCommand;
//
//    @Before
//    public void init(){
//        canvasCommand = new CanvasCommand(commandHistoryService, undoService,
//                canvasDrawer, originatorAggService, drawerFactoryService, objectMapper, redoService);
//
//    }
//
//    @Test
//    public void testExecute_SucceedWhenCommandHistoryIsEmpty() {
//        when(canvasDrawer.draw(any(char[][].class), any(int[].class))).thenReturn(createCanvasOne());
//        when(commandHistoryService.getLastOrEmpty()).thenReturn("");
//
//        canvasCommand.execute("C 10 4");
//
//        verify(undoService, times(1)).add(any());
//        verify(commandHistoryService, times(1)).add(any());
//        verify(redoService, times(1)).deleteAll();
//    }
//
//
//    private char[][] createCanvasOne(){
//        return new char[][]{
//                {'-','-','-','-','-','-','-','-','-','-','-','-',},
//                {'|', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
//                {'|', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
//                {'|', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
//                {'|', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
//                {'-','-','-','-','-','-','-','-','-','-','-','-',}
//        };
//    }
//
//
//}
