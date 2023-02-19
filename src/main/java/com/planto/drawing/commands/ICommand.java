package com.planto.drawing.commands;

import com.planto.drawing.enums.Command;

public interface ICommand {

    void execute(String[] params);

    Command getCommand();

}
