/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.sg.dao;

import com.sg.dto.item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author stwal
 */
public class vendingMachineDaoFileImpl implements vendingMachineDao {
    private Map <String, item> items = new HashMap<>();
    public static final String DELIMITER = "::";
    private final String VENDING_MACHINE_FILE;
    
    public vendingMachineDaoFileImpl() {
        VENDING_MACHINE_FILE = "VendingMachine.txt";
    }
    public vendingMachineDaoFileImpl(String testFile) {
        VENDING_MACHINE_FILE = testFile;
    }

    @Override
    public int getItemInventory(String name) throws vendingMachinePersistenceException {
        loadMachine();
        return items.get(name).getInventory();
    }

    @Override
    public void removeOneItemFromInventory(String name) throws vendingMachinePersistenceException {
        loadMachine();
        int prevInventory = items.get(name).getInventory();
        items.get(name).setInventory(prevInventory-1);
        writeMachine();
    }
    
  
    @Override
    public item getItem(String name) throws vendingMachinePersistenceException {
        loadMachine();
        return items.get(name);
    }
     
    @Override
    public Map<String,BigDecimal> getMapOfItemNamesInStockWithCosts() throws vendingMachinePersistenceException{
        loadMachine();
      
        
        Map<String, BigDecimal> itemsInStockWithCosts = items.entrySet()
                .stream()
                .filter(map -> map.getValue().getInventory() > 0)
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue().getCost()));
        
        return itemsInStockWithCosts;       
    }
    
    
    
    private String marshallItem (item anItem) {
        String itemAsText = anItem.getName() + DELIMITER;
        itemAsText += anItem.getCost() + DELIMITER;
        itemAsText += anItem.getInventory();
        return itemAsText;
    }
    
    
    
    private item unmarshallItem (String itemAsText){
        
        String [] itemTokens = itemAsText.split("::");
        String name = itemTokens[0];
        item itemFromFile = new item(name);
        BigDecimal bigDecimal = new BigDecimal(itemTokens[1]);
        itemFromFile.setCost(bigDecimal);
        itemFromFile.setInventory(Integer.parseInt(itemTokens[2]));
        return itemFromFile;
    }
    
    
    private void loadMachine() throws vendingMachinePersistenceException {
        Scanner scanner;

        try {
          
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(VENDING_MACHINE_FILE)));
        } catch (FileNotFoundException e) {
            throw new vendingMachinePersistenceException(
                    "FAILURE TO LOAD DATA", e);
        }
        String currentLine;
        item currentItem;
        
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            items.put(currentItem.getName(), currentItem);
        }
        scanner.close();
        }
    
    
    @Override
    public  List<item> getAllItems() throws vendingMachinePersistenceException {
        loadMachine();
        return new ArrayList(items.values());
    }
    
    
    private void writeMachine() throws vendingMachinePersistenceException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(VENDING_MACHINE_FILE));
        } catch (IOException e) {
            throw new vendingMachinePersistenceException("Could not save item data", e);
        }
        String itemAsText;
        List <item> itemList = this.getAllItems();
        for (item currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
        }

    
    }
