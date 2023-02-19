package com.planto.drawing.factories.impl;

import com.planto.drawing.commands.ICommand;
import com.planto.drawing.enums.Command;
import com.planto.drawing.factories.CommandFactoryService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CommandFactoryServiceImpl implements CommandFactoryService {

    private final Map<Command, ICommand> commandFactory;

    @Autowired
    public CommandFactoryServiceImpl(List<ICommand> commandFactory) {
        this.commandFactory = commandFactory.
                stream()
                .collect(Collectors.toMap(ICommand::getCommand, v -> v));
    }


    @Override
    public void execute(@NonNull final Command command, @NonNull final String[] params) {
        Optional.ofNullable(commandFactory.get(command))
                .orElseThrow(IllegalArgumentException::new)
                .execute(params);

    }
}
