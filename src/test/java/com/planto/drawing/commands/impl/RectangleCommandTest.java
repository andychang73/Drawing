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
public class RectangleCommandTest {

    @Mock private ShapeFactoryService shapeFactoryService;
    @Mock private OriginatorAggService originatorAggService;
    @Mock private AbstractCommand rectangleCommand;

    @Before
    public void init(){
        rectangleCommand = new RectangleCommand(originatorAggService,shapeFactoryService);
    }

    @Test
    public void testExecute_Succeed(){
        when(originatorAggService.getCanvasOrThrow()).thenReturn(createCanvas());
        when(shapeFactoryService.draw(any(), any(), any())).thenReturn(createCanvas());

        rectangleCommand.execute("R 1 1 3 3");

        verify(originatorAggService, times(1)).save(anyString(), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateInput_FailedWhenParamsLengthIsNotEqualTo4(){
        rectangleCommand.validateInput("R 1 1 3");
    }

    @Test
    public void testValidateInput_SucceedWhenParamsLengthIsEqualTo4(){
        Assert.assertTrue(rectangleCommand.validateInput("R 1 1 3 3"));
    }

    private char[][] createCanvas(){
        return new char[][]{
                {'-','-','-','-','-','-','-','-','-','-','-','-',},
                {'|', 'x','x','x',' ',' ',' ',' ',' ',' ',' ','|'},
                {'|', 'x',' ','x',' ',' ',' ',' ',' ',' ',' ','|'},
                {'|', 'x','x','x',' ',' ',' ',' ',' ',' ',' ','|'},
                {'|', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
                {'-','-','-','-','-','-','-','-','-','-','-','-',}
        };
    }
}
