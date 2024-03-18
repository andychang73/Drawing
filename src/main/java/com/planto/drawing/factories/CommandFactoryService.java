package com.planto.drawing.factories;

import com.planto.drawing.enums.Command;

public interface CommandFactoryService {

    void execute(Command command, String params);
}
