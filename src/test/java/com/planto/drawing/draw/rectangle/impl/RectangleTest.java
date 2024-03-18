package com.planto.drawing.draw.rectangle.impl;

import com.planto.drawing.draw.rectangle.IRectangle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class RectangleTest {

    @Mock private IRectangle rectangle;


    @Before
    public void init(){
        rectangle = new Rectangle();
    }

    @Test
    public void testDraw_Succeed(){
        char[][] canvas = rectangle.drawRectangle(getCanvas(), new int[]{1,1,3,3}, 'x');
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
