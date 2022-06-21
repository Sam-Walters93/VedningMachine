/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.sg.service;

import com.sg.dao.vendingMachinePersistenceException;
import com.sg.dto.item;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author stwal
 */
public interface VendingMachineServiceLayer {

    void checkIfEnoughMoney(item item, BigDecimal inputMoney)throws 
            insufficientFundsException;
    
    void removeOneItemFromInventory(String name) throws 
            NoItemInventoryException, 
            vendingMachinePersistenceException;
    
    Map<String, BigDecimal>  getItemsInStockWithCosts () throws 
            vendingMachinePersistenceException;

    item getItem(String name, BigDecimal inputMoney) throws 
            insufficientFundsException, 
            NoItemInventoryException, 
            vendingMachinePersistenceException;
    
    Map<BigDecimal, BigDecimal> getChangePerCoin(item item, BigDecimal money);
    
}
