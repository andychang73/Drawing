package com.planto.drawing.commands;

import com.planto.drawing.enums.Command;
import com.planto.drawing.utils.IntegerParser;

public abstract class AbstractCommand {

    public abstract void execute(String input);

    public abstract boolean validateInput(String input);

    public abstract Command getCommand();

    protected void validateParams(int[] params) {
        for (int i : params) {
            if (i < 1) {
                throw new IllegalArgumentException("Parameters must not be less than 1");
            }
        }
    }

    protected int[] parseParams(String input) {
        input = input.replaceAll("\\s+", " ");
        String[] inputArr = input.split(" ");
        if (inputArr[0].length() > 1) {
            throw new IllegalArgumentException("Invalid command");
        }
        int[] params = new int[inputArr.length - 1];
        for (int i = 1; i < inputArr.length; i++) {
            params[i - 1] = IntegerParser.parseStr(inputArr[i]);
        }
        return params;
    }

}
