package com.planto.drawing.draw.impl;

import com.planto.drawing.draw.IDraw;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class RectangleDrawerTest {

    @Mock private IDraw rectangleDrawer;

    @Before
    public void init(){
        rectangleDrawer = new RectangleDrawer();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenX1IsGreaterOrEqualToX2(){
        rectangleDrawer.draw(getCanvas(), new String[]{"4","1","3","3"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenY1IsGreaterOrEqualToY2(){
        rectangleDrawer.draw(getCanvas(), new String[]{"1","3","3","3"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenX1IsLessThanLowerBound(){
        rectangleDrawer.draw(getCanvas(), new String[]{"0","1","1","1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenX1IsGreaterThanUpperBound(){
        rectangleDrawer.draw(getCanvas(), new String[]{"11","1","1","1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenX2IsGreaterThanUpperBound(){
        rectangleDrawer.draw(getCanvas(), new String[]{"1","1","11","1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenY1IsLessThanLowerBound(){
        rectangleDrawer.draw(getCanvas(), new String[]{"1","0","1","1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenY1IsGreaterThanUpperBound(){
        rectangleDrawer.draw(getCanvas(), new String[]{"1","11","1","1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDraw_FailedWhenY2IsGreaterThanUpperBound(){
        rectangleDrawer.draw(getCanvas(), new String[]{"1","1","1","11"});
    }

    @Test
    public void testDraw_Succeed(){
        char[][] canvas = rectangleDrawer.draw(getCanvas(), new String[]{"1","1","3","3"});
        char[][] result = getResult();

        for(int i = 0; i < canvas.length; i++){
            for(int k = 0; k < canvas[i].length; k++){
                Assert.assertEquals(result[i][k], canvas[i][k]);
            }
        }
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

    private char[][] getResult(){
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
