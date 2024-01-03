/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customermanagerapp;

/**
 *
 * @author Praveen Korra
 */
class DeleteCommand implements Command {
    private final InMemoryDataStore dataStore;
     String logFilePath = "CustomerList.txt";
    LoggingMechanism logger = new LoggingMechanism(logFilePath);

    public DeleteCommand(InMemoryDataStore dataStore) {
        
        this.dataStore = dataStore;
    }
    @Override
    public void execute() {
    try {
        dataStore.deleteCustomer();
        dataStore.saveToFile("customer_data.txt");  // Save data to file after deleting a customer
    } catch (Exception e) {
        logger.log("An error occurred while deleting customers: " + e.getMessage());
        System.err.println("An error occurred while deleting a customer: " + e.getMessage());
    }
    }
}
