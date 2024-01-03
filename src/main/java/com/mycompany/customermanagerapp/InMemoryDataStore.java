/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customermanagerapp;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Praveen Korra
 */
class InMemoryDataStore {
    private final List<Customer> customers = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    
    String logFilePath = "CustomerList.txt";
    LoggingMechanism logger = new LoggingMechanism(logFilePath);
    
   public void listCustomers(String sortingOption, String sortingOrder) {
    List<Customer> sortedCustomers = new ArrayList<>(customers);
    if(!sortedCustomers.isEmpty()){

    switch (sortingOption) {
        case "name":
            sortedCustomers.sort(Comparator.comparing(Customer::getName));
            break;
        case "email":
            sortedCustomers.sort(Comparator.comparing(Customer::getEmail));
            break;
        case "number":
            sortedCustomers.sort(Comparator.comparing(Customer::getNumber));
            break;
        case "firstvisit":
            sortedCustomers.sort(Comparator.comparing(Customer::getFirstVisit));
            break;
        default:
            logger.log("Invalid sorting option. Displaying unsorted list.");
            System.out.println("Invalid sorting option. Displaying unsorted list.");
            break;
    }

    if ("desc".equals(sortingOrder)) {
        Collections.reverse(sortedCustomers);
    }

    System.out.println("List of Customers:");
    for (Customer customer : sortedCustomers) {
        System.out.println(customer.getName() + " - " + customer.getEmail() + " - " + customer.getNumber() + " - " + customer.getFirstVisit());
    }
    }else{
        logger.log("No customers in the list");
        System.out.println("No customers in the list");
    }
}


    public void addCustomer() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("customerList.txt"));
        System.out.println("Enter customer details:");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();
        if (!isValidEmail(email)) {
            logger.log("Invalid email format. Please enter a valid email.");
            System.out.println("Invalid email format. Please enter a valid email.");
            return;
        }

        System.out.print("Phone Number: ");
        String number = scanner.nextLine();
        if (!isValidPhoneNumber(number)) {
            logger.log("Invalid phone number format. Please enter a valid phone number.");
            System.out.println("Invalid phone number format. Please enter a valid phone number.");
            return;
        }

        System.out.print("Date of Visit(MM/DD/YYYY): ");
        String firstVisit = scanner.nextLine();
        if (!isValidDate(firstVisit)) {
            logger.log("Invalid date format. Please enter a valid date.");
            System.out.println("Invalid date format. Please enter a valid date.");
            return;
        }

        Customer newCustomer = new Customer(name, email, number, firstVisit);
        customers.add(newCustomer);
        bw.write(name + "," + email + "," + number +"," +firstVisit+"\n");
        logger.log("Customer added successfully!");
        System.out.println("Customer added successfully!");
    }

    public void deleteCustomer() {
        System.out.print("Enter the email of the customer to delete: ");
        String customerEmail = scanner.nextLine().toLowerCase();
         // Check if the email exists
    boolean customerFound = false;
    Iterator<Customer> iterator = customers.iterator();
    while (iterator.hasNext()) {
        Customer customer = iterator.next();
        if (customer.getEmail().equalsIgnoreCase(customerEmail)) {
            iterator.remove();  // Remove the customer
            customerFound = true;
            logger.log("Customer deleted successfully!");
            System.out.println("Customer deleted successfully!");
            break;  // Exit the loop once the customer is found and deleted
        }
    }

    // If customer not found, display a message
    if (!customerFound) {
        logger.log("Customer not found with email: " + customerEmail);
        System.out.println("Customer not found with email: " + customerEmail);
    }
    }
    public boolean searchCustomers(String searchCriteria) {
    System.out.println("Search results:");
    boolean found = false;

    for (Customer customer : customers) {
        if (customer.getName().toLowerCase().contains(searchCriteria)
                || customer.getEmail().toLowerCase().contains(searchCriteria)
                || customer.getNumber().toLowerCase().contains(searchCriteria)) {
            System.out.println(customer.getName() + " - " + customer.getEmail() + " - " + customer.getNumber() + " - " + customer.getFirstVisit());
            found = true;
        }
    }

    return found;
}
     public void saveToFile(String fileName) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
        for (Customer customer : customers) {
            writer.write(customer.getName() + "," + customer.getEmail() + "," + customer.getNumber() + "," + customer.getFirstVisit());
            writer.newLine();
        }
        logger.log("Data saved to file successfully!");
    } catch (IOException e) {
        logger.log("Error saving data to file: " + e.getMessage());
    }
}

    public void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        customers.clear();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] customerData = line.split(",");
            if (customerData.length == 4) {
                Customer customer = new Customer(customerData[0], customerData[1], customerData[2], customerData[3]);
                customers.add(customer);
            }
        }
        logger.log("Data loaded from file successfully!");
        } catch (IOException e) {
        logger.log("Error loading data from file: " + e.getMessage());
        }
    }
    private boolean isValidEmail(String email) {
        // Simple email format validation (presence of '@')
        return email.contains("@");
    }

    private boolean isValidPhoneNumber(String number) {
        // Simple phone number validation (numeric characters only)
        return number.matches("\\d+");
    }

    private boolean isValidDate(String date) {
        // Simple date validation (MM/DD/YYYY format)
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            dateFormat.setLenient(false);
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}