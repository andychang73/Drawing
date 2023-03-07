package com.planto.drawing.commands.impl;

import com.planto.drawing.aggregations.OriginatorAggService;
import com.planto.drawing.commands.AbstractCommand;
import com.planto.drawing.factories.ShapeFactoryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LineCommandTest {

    @Mock private ShapeFactoryService shapeFactoryService;
    @Mock private OriginatorAggService originatorAggService;

    @Mock private AbstractCommand lineCommand;

    @Before
    public void init(){
        lineCommand = new LineCommand(originatorAggService, shapeFactoryService);
    }

    @Test
    public void testExecute_Succeed() {

        when(originatorAggService.getCanvasOrThrow()).thenReturn(lineCanvas());
        when(shapeFactoryService.draw(any(),any(), any())).thenReturn(lineCanvas());

        lineCommand.execute("L 1 2 6 2");

        verify(originatorAggService, times(1)).save(anyString(),any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateInput_FailedWhenParamsLengthIsNotEqualTo4(){
        lineCommand.validateInput("L 1 2 6");
    }

    @Test
    public void testValidateInput_SucceedWhenParamsLengthIsEqualTo4(){
        Assert.assertTrue(lineCommand.validateInput("L 1 2 6 2"));
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
