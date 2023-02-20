package com.planto.drawing.aggregations.impl;

import com.planto.drawing.enums.Command;
import com.planto.drawing.factories.CommandFactoryService;
import com.planto.drawing.aggregations.DrawingClientAggService;
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
            String[] input = scanner.nextLine().trim().split(" ");
            try{
                commandFactoryService.execute(Command.value(input[0]), getParams(input));
            }catch (RuntimeException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    private String[] getParams(String[] input){
        if(input.length == 1){
            return new String[0];
        }
        String[] params = new String[input.length-1];
        System.arraycopy(input, 1, params, 0, input.length - 1);
        return params;
    }
}
