/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customermanagerapp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Praveen Korra
 */
public class LoggingMechanism {
    private final String logFilePath;

    public LoggingMechanism(String logFilePath) {
         this.logFilePath = logFilePath;
    }

    public void log(String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFilePath, true))) {
            LocalDateTime timestamp = LocalDateTime.now();
            String formattedTimestamp = timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String logEntry = "[" + formattedTimestamp + "] " + message;
            bw.write(logEntry);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error while logging: " + e.getMessage());
        }
    }
}