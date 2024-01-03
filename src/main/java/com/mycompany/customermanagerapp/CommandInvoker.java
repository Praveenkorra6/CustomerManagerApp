/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customermanagerapp;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Praveen Korra
 */
class CommandInvoker {
    private final Map<String, Command> commandMap;
    String logFilePath = "CustomerList.txt";
    LoggingMechanism logger = new LoggingMechanism(logFilePath);
   

    public CommandInvoker() {
        commandMap = new HashMap<>();
    }

    public void addCommand(String commandName, Command command) {
        commandMap.put(commandName, command);
        logger.log("Added command: " + commandName);
            
    }

    public void executeCommand(String commandName) {
        Command command = commandMap.get(commandName);
        if (command != null) {
            logger.log("Executing command: " + commandName);
            command.execute();
            logger.log("Command executed successfully: " + commandName);
        } else {
             logger.log("Command not found: " + commandName);
            System.out.println("Command not found: " + commandName);
        }
    }
}