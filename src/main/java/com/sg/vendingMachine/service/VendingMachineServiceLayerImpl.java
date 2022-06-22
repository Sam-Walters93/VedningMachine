/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.sg.vendingMachine.service;

import com.sg.vendingMachine.dao.vendingMachineAuditDao;
import com.sg.vendingMachine.dao.vendingMachineDao;
import com.sg.vendingMachine.dao.vendingMachinePersistenceException;
import com.sg.vendingMachine.dto.change;
import com.sg.vendingMachine.dto.item;
import java.math.BigDecimal;
import java.util.Map;


/**
 *
 * @author stwal
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{
 
    private vendingMachineAuditDao auditDao;
    private vendingMachineDao dao;
    
   
    public VendingMachineServiceLayerImpl(vendingMachineAuditDao auditDao, vendingMachineDao dao) {
        this.auditDao = auditDao;
        this.dao = dao;
    }

    @Override
    public void checkIfEnoughMoney(item item, BigDecimal inputMoney) throws insufficientFundsException {
      
        if (item.getCost().compareTo(inputMoney)==1) {
            throw new insufficientFundsException (
            "ERROR: insufficient funds, you have only input "+ inputMoney);  
        }
    }
    
    @Override
    public Map<String, BigDecimal>  getItemsInStockWithCosts () throws vendingMachinePersistenceException{
        
        Map<String, BigDecimal> itemsInStockWithCosts = dao.getMapOfItemNamesInStockWithCosts();
        return itemsInStockWithCosts;
    }
    
    @Override
    public Map<BigDecimal, BigDecimal> getChangePerCoin(item item, BigDecimal money) {
        BigDecimal itemCost = item.getCost();
        Map<BigDecimal, BigDecimal> changeDuePerCoin = change.changeDuePerCoin(itemCost, money);
        return changeDuePerCoin;
    }
    
    @Override
    public item getItem(String name, BigDecimal inputMoney) throws insufficientFundsException, NoItemInventoryException, vendingMachinePersistenceException {
        item wantedItem = dao.getItem(name);   
        
        if (wantedItem == null) {
            throw new NoItemInventoryException (
                "ERROR: there are no " + name + "'s in the vending machine.");
        }
        checkIfEnoughMoney(wantedItem,inputMoney);
        removeOneItemFromInventory(name);
        return wantedItem;
    }
    
    @Override
    public void removeOneItemFromInventory (String name) throws NoItemInventoryException, vendingMachinePersistenceException {
        
        if (dao.getItemInventory(name)>0) {
            dao.removeOneItemFromInventory(name);
            auditDao.writeAuditEntry(" One " + name + " removed");
        } else {
            throw new NoItemInventoryException (
            "ERROR: " + name + " is out of stock.");
        }
    }
}
