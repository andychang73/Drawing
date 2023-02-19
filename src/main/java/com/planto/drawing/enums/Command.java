package com.planto.drawing.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Command {

    C("C","Create Canvas"),
    L("L","Create Line"),
    R("R","Create Rectangle"),
    Z("Z","Undo"),
    X("X","Redo"),
    Q("Q","Quit")
    ;

    private final String str;
    private final String description;

    public static Command value(@NonNull final String str){
        return Arrays.stream(values())
                .filter(c -> c.getStr().equals(str))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Command"));
    }
}
