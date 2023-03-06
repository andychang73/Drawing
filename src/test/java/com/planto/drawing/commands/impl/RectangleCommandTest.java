//package com.planto.drawing.commands.impl;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.planto.drawing.commands.AbstractCommand;
//import com.planto.drawing.draw.IDraw;
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
//public class RectangleCommandTest {
//
//    @Mock private IDraw rectangleDrawer;
//    @Mock private ObjectMapper objectMapper;
//    @Mock private UndoService undoService;
//    @Mock private RedoService redoService;
//    @Mock private CommandHistoryService historyService;
//    @Mock private AbstractCommand rectangleCommand;
//
//    @Before
//    public void init(){
//        rectangleCommand = new RectangleCommand(rectangleDrawer, objectMapper,
//                undoService, redoService, historyService);
//    }
//
//    @Test
//    public void testExecute_Succeed(){
//        when(rectangleDrawer.draw(any(),any())).thenReturn(createCanvas());
//
//        rectangleCommand.execute(new String[]{"1","1","3","3"});
//
//        verify(undoService, times(1)).add(any());
//        verify(historyService, times(1)).add(any());
//        verify(redoService, times(1)).deleteAll();
//    }
//
//    private char[][] createCanvas(){
//        return new char[][]{
//                {'-','-','-','-','-','-','-','-','-','-','-','-',},
//                {'|', 'x','x','x',' ',' ',' ',' ',' ',' ',' ','|'},
//                {'|', 'x',' ','x',' ',' ',' ',' ',' ',' ',' ','|'},
//                {'|', 'x','x','x',' ',' ',' ',' ',' ',' ',' ','|'},
//                {'|', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
//                {'-','-','-','-','-','-','-','-','-','-','-','-',}
//        };
//    }
//}
