package com.planto.drawing.commands.impl;

import com.planto.drawing.commands.ICommand;
import com.planto.drawing.enums.Command;
import org.springframework.stereotype.Component;

@Component
public class QuitCommand implements ICommand {

    @Override
    public void execute(String[] params) {
        System.out.println("Quitting Program........ ");
        System.exit(0);
    }

    @Override
    public Command getCommand() {
        return Command.Q;
    }
}
