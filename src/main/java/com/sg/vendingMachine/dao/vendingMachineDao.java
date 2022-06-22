/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.vendingMachine.dao;

import com.sg.vendingMachine.dto.item;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author stwal
 */
public interface vendingMachineDao {

    void removeOneItemFromInventory(String name) throws vendingMachinePersistenceException;
    
    List<item> getAllItems() throws vendingMachinePersistenceException ;
    
    int getItemInventory(String name) throws vendingMachinePersistenceException;

    item getItem(String name)throws vendingMachinePersistenceException;

    Map<String,BigDecimal> getMapOfItemNamesInStockWithCosts()throws vendingMachinePersistenceException;  
}
