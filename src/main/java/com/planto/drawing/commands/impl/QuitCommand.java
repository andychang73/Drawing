package com.planto.drawing.commands.impl;

import com.planto.drawing.commands.AbstractCommand;
import com.planto.drawing.enums.Command;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class QuitCommand extends AbstractCommand {

    @Override
    public void execute(@NonNull final String input) {
        System.out.println("Quitting Program........ ");
        System.exit(0);
    }

    @Override
    public boolean validateInput(@NonNull final String input) {
        int[] params = parseParams(input);
        if(params.length != 0){
            throw new IllegalArgumentException("Invalid Quit parameters");
        }
        return true;
    }

    @Override
    public Command getCommand() {
        return Command.Q;
    }
}
