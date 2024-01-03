/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customermanagerapp;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Praveen Korra
 */
class ListCommand implements Command {
    private final InMemoryDataStore dataStore;
    
   String logFilePath = "CustomerList.txt";
    LoggingMechanism logger = new LoggingMechanism(logFilePath);
    
    public ListCommand(InMemoryDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void execute() {
          Scanner scanner = new Scanner(System.in);
          try{

        System.out.println("Enter sorting option (name, email, number, firstVisit): ");
        String sortingOption = scanner.nextLine().toLowerCase();

        if (!isValidSortingOption(sortingOption)) {
            logger.log( "Invalid sorting option: {0}. Displaying unsorted list.");
            System.out.println("Invalid sorting option. Displaying unsorted list.");
            return;
        }

        System.out.println("Enter sorting order (asc or desc): ");
        String sortingOrder = scanner.nextLine().toLowerCase();

        if (!isValidSortingOrder(sortingOrder)) {
            logger.log( "Invalid sorting order: {0}. Displaying unsorted list.");
            System.out.println("Invalid sorting order. Displaying unsorted list.");
            return;
        }
            dataStore.listCustomers(sortingOption, sortingOrder);
        }catch(Exception e){
            logger.log( "An error occurred while listing customers: " );
            System.err.println("An error occurred while listing a customer: " + e.getMessage( ));
        }
    }

    private boolean isValidSortingOption(String sortingOption) {
         return Arrays.asList("name", "email", "number", "firstVisit").contains(sortingOption);
    }

    private boolean isValidSortingOrder(String sortingOrder) {
        return sortingOrder.equals("asc") || sortingOrder.equals("desc");
    }
    }