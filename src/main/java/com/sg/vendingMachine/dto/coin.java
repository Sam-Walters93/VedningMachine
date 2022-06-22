/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.sg.vendingMachine.dto;

/**
 *
 * @author stwal
 */
public enum coin {
    QUARTER(25), DIME(10), NICKEL(5), PENNY(1);
    private final int value;
    
    coin (int value) {
        this.value = value;
    }
    
    private int getValue() {
        return value;
    }
         
    public String toString(){
        switch (this) {
            case QUARTER:
                return "25";
            case DIME:
                return "10";
            case NICKEL:
                return "5";
            case PENNY:
                return "1";
        }
        return null;
    }
    
    
}
