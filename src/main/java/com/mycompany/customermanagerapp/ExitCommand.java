/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customermanagerapp;

/**
 *
 * @author Praveen Korra
 */
class ExitCommand implements Command {
    String logFilePath = "CustomerList.txt";
    LoggingMechanism logger = new LoggingMechanism(logFilePath);
    @Override
    public void execute() {
        logger.log("Exiting the application");
        System.out.println("Exiting the application");
        System.exit(0);
    }
}