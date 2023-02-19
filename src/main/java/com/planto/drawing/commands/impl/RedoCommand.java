package com.planto.drawing.commands.impl;

import com.planto.drawing.commands.ICommand;
import com.planto.drawing.enums.Command;
import org.springframework.stereotype.Component;

@Component
public class RedoCommand implements ICommand {
    @Override
    public void execute(String[] params) {

    }

    @Override
    public Command getCommand() {
        return Command.X;
    }
}
