package com.planto.drawing.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Printer {

    public static void print(char[][] canvas){
        for (char[] canva : canvas) {
            for (char c : canva) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
