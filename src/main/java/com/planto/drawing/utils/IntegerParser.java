package com.planto.drawing.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class IntegerParser {

    public int parseStr(@NonNull final String str){
        try{
            return Integer.parseInt(str);
        }catch (NumberFormatException ex){
            throw new IllegalArgumentException("Input String cannot be parsed to Integer");
        }
    }
}
