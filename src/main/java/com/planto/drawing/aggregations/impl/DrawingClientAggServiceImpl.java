package com.planto.drawing.aggregations.impl;

import com.planto.drawing.aggregations.DrawingClientAggService;
import com.planto.drawing.enums.Command;
import com.planto.drawing.factories.CommandFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class DrawingClientAggServiceImpl implements DrawingClientAggService {

    private final CommandFactoryService commandFactoryService;

    @Autowired
    public DrawingClientAggServiceImpl(CommandFactoryService commandFactoryService) {
        this.commandFactoryService = commandFactoryService;
    }

    @Override
    public void draw() {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("enter command: ");
            String input = scanner.nextLine().trim();
            if(input.length() == 0){
                continue;
            }
            try{
                commandFactoryService.execute(Command.value(input.charAt(0)), input);
            }catch (RuntimeException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
