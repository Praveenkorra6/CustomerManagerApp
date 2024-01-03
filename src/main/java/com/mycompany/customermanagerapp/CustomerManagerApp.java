/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.customermanagerapp;

import java.util.Scanner;

/**
 *
 * @author Praveen Korra
 */
public class CustomerManagerApp {

    public static void main(String[] args){
    String logFilePath = "CustomerList.txt";
    LoggingMechanism logger = new LoggingMechanism(logFilePath);
        try{
        InMemoryDataStore dataStore = new InMemoryDataStore();
        CommandInvoker invoker = new CommandInvoker();

        // Setting up commands with the dataStore instance
        Command listCommand = new ListCommand(dataStore);
        Command addCommand = new AddCommand(dataStore);
        Command delCommand = new DeleteCommand(dataStore);
        Command helpCommand = (Command) new HelpCommand();
        Command exitCommand = (Command) new ExitCommand();
        Command searchCommand = new SearchCommand(dataStore);
        
        
      
        
        // Add the commands to the invoker
        invoker.addCommand("list", listCommand);
        invoker.addCommand("add", addCommand);
        invoker.addCommand("delete", delCommand);
        invoker.addCommand("help", helpCommand);
        invoker.addCommand("exit", exitCommand);
        invoker.addCommand("search", searchCommand);
        
        
        Scanner scanner = new Scanner(System.in);
        dataStore.loadFromFile("customer_data.txt");
        while (true) {
            System.out.print("Enter command (type 'help' for commands): ");
            String input = scanner.nextLine().toLowerCase();  // Convert input to lowercase
            switch (input) {
                case "list":
                    invoker.executeCommand("list");
                    break;
                case "add":
                    invoker.executeCommand("add");
                    break;
                case "delete":
                    invoker.executeCommand("delete");
                    break;
                case "help":
                    invoker.executeCommand("help");
                    break;
                case "search":
                    invoker.executeCommand("search");
                    break;
                case "exit":
                    dataStore.saveToFile("customer_data.txt");
                    invoker.executeCommand("exit");
                    break;
                default:
                    logger.log("Invalid command: {0}");
                    System.out.println("Invalid command. Type 'help' for commands.");
                    continue;
            }       
        }
        }catch(Exception e){
                logger.log("An unexpected error occurred: ");
         }
    }
}

        

