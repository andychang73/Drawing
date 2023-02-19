package com.planto.drawing.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Printer {

    public static void print(char[][] canvas){
        for(int i = 0; i < canvas.length; i++){
            for(int k = 0; k < canvas[i].length; k++){
                System.out.print(canvas[i][k]);
            }
            System.out.println();
        }
    }
}
