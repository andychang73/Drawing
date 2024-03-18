package com.planto.drawing.commands.impl;

import com.planto.drawing.aggregations.OriginatorAggService;
import com.planto.drawing.commands.AbstractCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class RedoCommandTest {

    @Mock private OriginatorAggService originatorAggService;
    @Mock private AbstractCommand redoCommand;

    @Before
    public void init(){
        redoCommand = new RedoCommand(originatorAggService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateInput_FailedWhenParamsLengthIsNotEqualToZero() {
        redoCommand.validateInput("X 1");
    }

    @Test
    public void testValidateInput_SucceedWhenParamsLengthIsEqualToZero(){
        Assert.assertTrue(redoCommand.validateInput("X"));
    }
}
