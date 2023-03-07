package com.planto.drawing.draw.impl;

import com.planto.drawing.draw.IShapeDrawer;
import com.planto.drawing.draw.line.ILine;
import com.planto.drawing.draw.line.impl.HorizontalLine;
import com.planto.drawing.draw.line.impl.VerticalLine;
import com.planto.drawing.services.SymbolService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;


public class LineDrawerTest {

    @Mock private ILine horizontal;
    @Mock private ILine vertical;
    @Mock private List<ILine> lines;
    @Mock private IShapeDrawer lineDrawer;
    @Mock private SymbolService symbolService;

    @Before
    public void init(){
        horizontal = new HorizontalLine();
        vertical = new VerticalLine();
        lines = Arrays.asList(horizontal, vertical);
        lineDrawer = new LineDrawer(lines, symbolService);
    }

    @Test
    public void testDraw_FailedWhenX1IsLessThanLowerBound(){
        boolean validate = lineDrawer.validate(getCanvas(), new int[]{0,1,1,1});
        Assert.assertFalse(validate);
    }

    @Test
    public void testDraw_FailedWhenX1IsGreaterThanUpperBound(){
        boolean validate = lineDrawer.validate(getCanvas(), new int[]{11,1,1,1});
        Assert.assertFalse(validate);
    }

    @Test
    public void testDraw_FailedWhenY1IsLessThanLowerBound(){
        boolean validate = lineDrawer.validate(getCanvas(), new int[]{1,0,1,1});
        Assert.assertFalse(validate);
    }

    @Test
    public void testDraw_FailedWhenY1IsGreaterThanUpperBound(){
        boolean validate = lineDrawer.validate(getCanvas(), new int[]{1,5,1,1});
        Assert.assertFalse(validate);
    }

    @Test
    public void testDraw_FailedWhenX2IsLessThanLowerBound(){
        boolean validate = lineDrawer.validate(getCanvas(), new int[]{1,1,0,1});
        Assert.assertFalse(validate);
    }

    @Test
    public void testDraw_FailedWhenX2IsGreaterThanUpperBound(){
        boolean validate = lineDrawer.validate(getCanvas(), new int[]{1,1,11,1});
        Assert.assertFalse(validate);
    }

    @Test
    public void testDraw_FailedWhenY2IsLessThanLowerBound(){
        boolean validate = lineDrawer.validate(getCanvas(), new int[]{1,1,1,0});
        Assert.assertFalse(validate);
    }

    @Test
    public void testDraw_FailedWhenY2IsGreaterThanUpperBound(){
        boolean validate = lineDrawer.validate(getCanvas(), new int[]{1,1,1,5});
        Assert.assertFalse(validate);
    }

    // x:1-10 y:1 - 4
    private char[][] getCanvas(){
        return new char[][]{
                {'-','-','-','-','-','-','-','-','-','-','-','-',},
                {'|', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
                {'|', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
                {'|', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
                {'|', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
                {'-','-','-','-','-','-','-','-','-','-','-','-',}
        };
    }


}
