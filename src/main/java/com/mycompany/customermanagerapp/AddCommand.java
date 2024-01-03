/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customermanagerapp;

import java.io.IOException;

/**
 *
 * @author Praveen Korra
 */
class AddCommand implements Command {
    private final InMemoryDataStore dataStore;
    String logFilePath = "CustomerList.txt";
    LoggingMechanism logger = new LoggingMechanism(logFilePath);


    public AddCommand(InMemoryDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
   public void execute() {
    try {
        dataStore.addCustomer();
        dataStore.saveToFile("customer_data.txt");  // Save data to file after adding a customer
    } catch (IOException e) {
        logger.log("An error occurred while adding customers: " + e.getMessage());
        System.err.println("An error occurred while adding a customer: " + e.getMessage());
        }
    }
}