package com.planto.drawing.draw.impl;

import com.planto.drawing.draw.IDraw;
import com.planto.drawing.draw.line.ILine;
import com.planto.drawing.draw.line.impl.HorizontalLine;
import com.planto.drawing.draw.line.impl.VerticalLine;
import com.planto.drawing.services.SymbolService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;


public class LineDrawerTest {

    @Mock private ILine horizontal;
    @Mock private ILine vertical;
    @Mock private List<ILine> lines;
    @Mock private IDraw lineDrawer;
    @Mock private SymbolService symbolService;

    @Before
    public void init(){
        horizontal = new HorizontalLine();
        vertical = new VerticalLine();
        lines = Arrays.asList(horizontal, vertical);
        lineDrawer = new LineDrawer(lines, symbolService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenParamsLengthNotEqualToFour(){
        lineDrawer.draw(getCanvas(), new String[]{"1","1","1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenX1IsLessThanLowerBound(){
        lineDrawer.draw(getCanvas(), new String[]{"0","1","1","1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenX1IsGreaterThanUpperBound(){
        lineDrawer.draw(getCanvas(), new String[]{"11","1","1","1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenY1IsLessThanLowerBound(){
        lineDrawer.draw(getCanvas(), new String[]{"1","0","1","1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenY1IsGreaterThanUpperBound(){
        lineDrawer.draw(getCanvas(), new String[]{"1","5","1","1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenX2IsLessThanLowerBound(){
        lineDrawer.draw(getCanvas(), new String[]{"1","1","0","1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenX2IsGreaterThanUpperBound(){
        lineDrawer.draw(getCanvas(), new String[]{"1","1","11","1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenY2IsLessThanLowerBound(){
        lineDrawer.draw(getCanvas(), new String[]{"1","1","1","0"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenY2IsGreaterThanUpperBound(){
        lineDrawer.draw(getCanvas(), new String[]{"1","1","1","5"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenEnteredInvalidLineType(){
        lineDrawer.draw(getCanvas(), new String[]{"1","2","3","4"});
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
