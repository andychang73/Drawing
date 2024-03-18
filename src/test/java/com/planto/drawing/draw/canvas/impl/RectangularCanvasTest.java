package com.planto.drawing.draw.canvas.impl;

import com.planto.drawing.draw.canvas.ICanvas;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class RectangularCanvasTest {

    @Mock private ICanvas rectangularCanvas;

    @Before
    public void init(){
        rectangularCanvas = new RectangularCanvas();
    }

    @Test
    public void drawCanvas_Succeed(){
        char[][] canvas = rectangularCanvas.drawCanvas(new int[]{10,4});
        char[][] result = getResult();

        for(int i = 0; i < canvas.length; i++){
            for(int k = 0; k < canvas[i].length; k++){
                Assert.assertEquals(result[i][k], canvas[i][k]);
            }
        }

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
