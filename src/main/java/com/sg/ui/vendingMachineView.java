/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.sg.ui;

import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author stwal
 */
public class vendingMachineView {
    
    private userIO io;
    
    public vendingMachineView (userIO io){
        this.io = io;
    }

    public BigDecimal getMoney() {
        return io.readBigDecimal("Please add money before making a selection");
    }
    
    public void displayMenuBanner() {
        io.print("===MENU===");
    }
    
    public void displayMenu(Map<String, BigDecimal> itemsInStockWithCosts){
        itemsInStockWithCosts.entrySet().forEach(entry ->{
        System.out.println(entry.getKey() + ": $" +entry.getValue());
        });
    }
    
    public String getItemSelection(){
        return io.readString("Please select an item from the menu above or 'exit' to quit");
    }

    public void displayEnjoyBanner(String name) {
        io.print("Here is your change.");
        io.print("Enjoy your " + name + "!");
    }
    
    public void displayInsufficientFundsMsg(BigDecimal money){
        io.print("Insufficent funds, you only have input $"+ money);
    }
    
    public void displayItemOutOfStockMsg(String name){
        io.print("Error, " + name + " is out of stock.");
    }   

    public void displayChangeDuePerCoin(Map<BigDecimal, BigDecimal> changeDuePerCoin) {
        changeDuePerCoin.entrySet().forEach(entry ->{
                 System.out.println(entry.getKey() + "c : " +entry.getValue());
        });
    }

    public void displayExitBanner() {
        io.print("----GOODBYE----");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("----UNKNOWN COMMAND----");
    }
    
    public void displayErrorMessage (String errorMsg) {
        io.print("----ERROR----");
        io.print(errorMsg);
    }
    
    public void displayPleaseTryAgainMsg() {
        io.print("Please select something else.");
    }
}
