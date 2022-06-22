/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.sg.vendingMachine.service;

/**
 *
 * @author stwal
 */
public class insufficientFundsException extends Exception {
    
    public insufficientFundsException(String message) {
        super(message);
    }
    
    public insufficientFundsException(String message, Throwable cause) {
        super(message,cause);
    }
}
