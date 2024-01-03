/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customermanagerapp;

import java.io.Serializable;

/**
 *
 * @author Praveen Korra
 */
class Customer implements Serializable {
    private final String name;
    private final String email;
    private final String number;
    private final String firstVisit;
    String logFilePath = "CustomerList.txt";
    LoggingMechanism logger = new LoggingMechanism(logFilePath);

    public Customer(String name, String email, String number, String firstVisit) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.firstVisit = firstVisit;
        // Log creation of a new customer instance
        logger.log( "Created a new Customer: {0} - {1} - {2} - {3}"+name +" - "+ email+" - " + number+" - " +firstVisit);
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    public String getFirstVisit() {
        return firstVisit;
    }
    
}
