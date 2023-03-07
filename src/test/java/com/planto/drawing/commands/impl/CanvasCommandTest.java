package com.planto.drawing.commands.impl;

import com.planto.drawing.aggregations.OriginatorAggService;
import com.planto.drawing.commands.AbstractCommand;
import com.planto.drawing.factories.CanvasFactoryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CanvasCommandTest {

    @Mock private CanvasFactoryService canvasFactoryService;
    @Mock private OriginatorAggService originatorAggService;

    @Mock private AbstractCommand canvasCommand;

    @Before
    public void init(){
        canvasCommand = new CanvasCommand(originatorAggService, canvasFactoryService);

    }

    @Test
    public void testExecute_Succeed() {

        when(canvasFactoryService.create(any(),any())).thenReturn(getResult());

        canvasCommand.execute("C 10 4");

        verify(originatorAggService, times(1)).save(anyString(), any(char[][].class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateInput_FailedWhenParamsLengthIsNotEqualTo2(){
        canvasCommand.validateInput("C 20");
    }

    @Test
    public void testValidateInput_SucceedWhenParamsLengthIsEqualTo2(){
        Assert.assertTrue(canvasCommand.validateInput("C 20 4"));
    }

    private char[][] getResult() {
        return new char[][]{
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-',},
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-',}
        };
    }


}
