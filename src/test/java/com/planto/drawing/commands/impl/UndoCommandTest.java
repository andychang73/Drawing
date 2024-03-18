package com.planto.drawing.commands.impl;

import com.planto.drawing.aggregations.OriginatorAggService;
import com.planto.drawing.commands.AbstractCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class UndoCommandTest {

    @Mock private OriginatorAggService originatorAggService;
    @Mock private AbstractCommand undoCommand;

    @Before
    public void init(){
        undoCommand = new UndoCommand(originatorAggService);
    }

    @Test(expected = RuntimeException.class)
    public void testValidateInput_FailedWhenParamsLengthNotEqualToZero(){
        undoCommand.validateInput("Z 1");
    }

    @Test
    public void testValidateInput_SucceedWhenParamsLengthIsEqualToZero(){
        Assert.assertTrue(undoCommand.validateInput("Z"));
    }
}
