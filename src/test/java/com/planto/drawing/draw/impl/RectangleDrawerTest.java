package com.planto.drawing.draw.impl;

import com.planto.drawing.draw.IShapeDrawer;
import com.planto.drawing.draw.rectangle.IRectangle;
import com.planto.drawing.services.SymbolService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RectangleDrawerTest {

    @Mock private IRectangle rectangle;
    @Mock private List<IRectangle> list;
    @Mock private IShapeDrawer rectangleDrawer;
    @Mock private SymbolService symbolService;

    @Before
    public void init(){
        list.add(rectangle);
        rectangleDrawer = new RectangleDrawer(list, symbolService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenX1IsGreaterOrEqualToX2(){
        rectangleDrawer.validate(getCanvas(), new int[]{4,1,3,3});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenY1IsGreaterOrEqualToY2(){
        rectangleDrawer.validate(getCanvas(), new int[]{1,3,3,3});
    }

    @Test
    public void testDraw_FailedWhenX2IsGreaterThanUpperBound(){
        boolean validate = rectangleDrawer.validate(getCanvas(), new int[]{1,1,11,2});
        Assert.assertFalse(validate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenY1IsLessThanLowerBound(){
        rectangleDrawer.validate(getCanvas(), new int[]{1,0,1,1});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenY1IsGreaterThanUpperBound(){
        rectangleDrawer.validate(getCanvas(), new int[]{1,11,1,1});
    }

    @Test
    public void testDraw_FailedWhenY2IsGreaterThanUpperBound(){
        boolean validate = rectangleDrawer.validate(getCanvas(), new int[]{1,1,2,11});
        Assert.assertFalse(validate);
    }

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
