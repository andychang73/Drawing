package com.planto.drawing.draw.line.impl;

import com.planto.drawing.draw.line.ILine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;


public class HorizontalLineTest {

    @Mock private ILine horizontal;

    @Before
    public void init(){
        horizontal = new HorizontalLine();
    }

    @Test
    public void testDrawLine_Succeed(){
        char[][] canvas = horizontal.drawLine(getCanvas(), new int[]{1,2,4,2}, 'x');
        char[][] result = getResult();

        for(int i = 0; i < canvas.length; i++){
            for(int k = 0; k < canvas[i].length; k++){
                Assert.assertEquals(result[i][k], canvas[i][k]);
            }
        }
    }

    @Test
    public void testCheckLineType_TrueWhenY1AndY2IsEqual(){
        boolean result = horizontal.checkLineType(new int[]{1,2,3,2});
        Assert.assertTrue(result);
    }

    @Test
    public void testCheckLineType_FalseWhenY1AndY2IsNotEqual(){
        boolean result = horizontal.checkLineType(new int[]{1,2,3,4});
        Assert.assertFalse(result);
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
                {'|', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
                {'|', 'x','x','x','x',' ',' ',' ',' ',' ',' ','|'},
                {'|', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
                {'|', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
                {'-','-','-','-','-','-','-','-','-','-','-','-',}
        };
    }
}
