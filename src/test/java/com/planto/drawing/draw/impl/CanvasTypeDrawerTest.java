//package com.planto.drawing.draw.impl;
//
//import com.planto.drawing.draw.IShapeDrawer;
//import com.planto.drawing.draw.canvas.ICanvas;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//
//import java.util.List;
//
//public class CanvasTypeDrawerTest {
//
//    @Mock private ICanvas rectangularCanvas;
//    @Mock private IShapeDrawer canvasDrawer;
//
//    @Before
//    public void init(){
//        canvasDrawer = new CanvasDrawer(List.of(rectangularCanvas));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testDraw_FailedWhenParamsLengthIsNotEqualToTwo(){
//        canvasDrawer.draw(new char[][]{}, new int[1]);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testDraw_FailedWhenWidthIsLessThanOne(){
//        canvasDrawer.draw(new char[][]{}, new int[]{0,1});
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testDraw_FailedWhenHeightIsLessThanOne(){
//        canvasDrawer.draw(new char[][]{}, new int[]{1,0});
//    }
//
//    @Test
//    public void testDraw_Succeed(){
//        char[][] canvas = canvasDrawer.draw(new char[][]{}, new int[]{10,4});
//        char[][] canvasResult = getCanvasResult();
//
//        for(int i = 0; i < canvas.length; i++){
//            for(int k = 0; k < canvas[i].length; k++){
//                Assert.assertEquals(canvasResult[i][k], canvas[i][k]);
//            }
//        }
//    }
//
//    private char[][] getCanvasResult(){
//        return new char[][]{
//                {'-','-','-','-','-','-','-','-','-','-','-','-',},
//                {'|', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
//                {'|', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
//                {'|', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
//                {'|', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
//                {'-','-','-','-','-','-','-','-','-','-','-','-',}
//        };
//    }
//
//}
