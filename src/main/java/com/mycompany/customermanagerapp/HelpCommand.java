/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customermanagerapp;

/**
 *
 * @author Praveen Korra
 */
class HelpCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Available Commands:");
        System.out.println("1. list - List all customers");
        System.out.println("2. add - Add a new customer");
        System.out.println("3. delete - Delete a customer");
        System.out.println("4. help - Display help menu");
        System.out.println("5. exit - Exit the application");
        System.out.println("6. search - Search the customers");
    }
}