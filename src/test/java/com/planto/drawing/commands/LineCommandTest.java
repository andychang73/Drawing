package com.planto.drawing.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.planto.drawing.commands.impl.LineCommand;
import com.planto.drawing.draw.IDraw;
import com.planto.drawing.services.CommandHistoryService;
import com.planto.drawing.services.RedoService;
import com.planto.drawing.services.UndoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LineCommandTest {

    @Mock private IDraw lineDrawer;
    @Mock private ObjectMapper objectMapper;
    @Mock private UndoService undoService;
    @Mock private RedoService redoService;
    @Mock private CommandHistoryService historyService;
    @Mock private ICommand lineCommand;

    @Before
    public void init(){
        lineCommand = new LineCommand(objectMapper, undoService,
                lineDrawer, redoService, historyService);
    }

    @Test
    public void testExecute_Succeed() {

        when(lineDrawer.draw(any(), any())).thenReturn(lineCanvas());

        lineCommand.execute(new String[]{"1","1","3","1"});

        verify(undoService, times(1)).add(any());
        verify(historyService, times(1)).add(any());
        verify(redoService, times(1)).deleteAll();
    }

    private char[][] lineCanvas(){
        return new char[][]{
                {'-','-','-','-','-'},
                {'|','x','x','x','|'},
                {'|',' ',' ',' ','|'},
                {'-','-','-','-','-'}
        };
    }
}
