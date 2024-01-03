/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customermanagerapp;

import java.util.Scanner;

/**
 *
 * @author Praveen Korra
 */
class SearchCommand implements Command {
    Scanner scanner = new Scanner(System.in);
    private final InMemoryDataStore dataStore;
    String logFilePath = "CustomerList.txt";
    LoggingMechanism logger = new LoggingMechanism(logFilePath);
    public SearchCommand(InMemoryDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void execute() {
        System.out.println("Enter search criteria (name, email, or phone number): ");
        String searchCriteria = scanner.nextLine().toLowerCase();
        try{
        boolean found = dataStore.searchCustomers(searchCriteria);
        if (!found) {
            System.out.println("No customers found with the specified " + searchCriteria + ".");
        }
        }catch(Exception e){
            logger.log( "An error occurred while Searching customers: " );
            System.err.println("An error occurred while Searching a customer: "+ e.getMessage());
        }
       
    }
}
