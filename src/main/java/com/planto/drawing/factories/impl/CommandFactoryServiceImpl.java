package com.planto.drawing.factories.impl;

import com.planto.drawing.commands.AbstractCommand;
import com.planto.drawing.enums.Command;
import com.planto.drawing.factories.CommandFactoryService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CommandFactoryServiceImpl implements CommandFactoryService {

    private final Map<Command, AbstractCommand> commandFactory;

    @Autowired
    public CommandFactoryServiceImpl(List<AbstractCommand> commandFactory) {
        this.commandFactory = Collections.unmodifiableMap(
                commandFactory.stream()
                        .collect(Collectors.toMap(AbstractCommand::getCommand, v -> v))
        );
    }


    @Override
    public void execute(@NonNull final Command command, @NonNull final String input) {
        Optional.ofNullable(commandFactory.get(command))
                .filter(factory -> factory.validateInput(input))
                .stream()
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .execute(input);

    }
}
