package com.planto.drawing.draw.line.impl;

import com.planto.drawing.draw.line.ILine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class VerticalLineTest {

    @Mock private ILine vertical;

    @Before
    public void init(){
        vertical = new VerticalLine();
    }

    @Test
    public void testDrawLine_Succeed(){
        char[][] canvas = vertical.drawLine(getCanvas(), new int[]{3,1,3,4}, 'x');
        char[][] result = getResult();

        for(int i = 0; i < canvas.length; i++){
            for(int k = 0; k < canvas[i].length; k++){
                Assert.assertEquals(result[i][k], canvas[i][k]);
            }
        }
    }

    @Test
    public void testCheckLineType_TrueWhenX1AndX2IsEqual(){
        boolean result = vertical.checkLineType(new int[]{3,1,3,4});
        Assert.assertTrue(result);
    }

    @Test
    public void testCheckLineType_FalseWhenX1AndX2IsNotEqual(){
        boolean result = vertical.checkLineType(new int[]{3,1,2,4});
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
                {'|', ' ',' ','x',' ',' ',' ',' ',' ',' ',' ','|'},
                {'|', ' ',' ','x',' ',' ',' ',' ',' ',' ',' ','|'},
                {'|', ' ',' ','x',' ',' ',' ',' ',' ',' ',' ','|'},
                {'|', ' ',' ','x',' ',' ',' ',' ',' ',' ',' ','|'},
                {'-','-','-','-','-','-','-','-','-','-','-','-',}
        };
    }
}
