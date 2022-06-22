/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.sg.vendingMachine.controller;

import com.sg.vendingMachine.dao.vendingMachinePersistenceException;
import com.sg.vendingMachine.dto.item;
import com.sg.vendingMachine.service.NoItemInventoryException;
import com.sg.vendingMachine.service.VendingMachineServiceLayer;
import com.sg.vendingMachine.service.insufficientFundsException;
import com.sg.vendingMachine.ui.userIO;
import com.sg.vendingMachine.ui.userIOConsoleImpl;
import com.sg.vendingMachine.ui.vendingMachineView;
import java.math.BigDecimal;
import java.util.Map;


/**
 *
 * @author stwal
 */


public class vendingMachineController {
    private userIO io = new userIOConsoleImpl();
    private vendingMachineView view;
    private VendingMachineServiceLayer service;

  
    public vendingMachineController(vendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        String itemSelection = "";
        BigDecimal inputMoney;
        view.displayMenuBanner();
        try {
            getMenu();
        } catch (vendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        inputMoney = getMoney();
            while (keepGoing) {
            try {
             
                itemSelection = getItemSelection();
                
               
                if (itemSelection.equalsIgnoreCase("Exit")) {
                    keepGoing = false;
                    break;
                }
                getItem(itemSelection, inputMoney);
                keepGoing = false;
                break;

            } catch (insufficientFundsException | NoItemInventoryException | vendingMachinePersistenceException e) {
                view.displayErrorMessage(e.getMessage());
                view.displayPleaseTryAgainMsg();
            }
            }
            exitMessage();

    }
    private void getMenu() throws vendingMachinePersistenceException {
        Map<String, BigDecimal> itemsInStockWithCosts = service.getItemsInStockWithCosts();
        view.displayMenu(itemsInStockWithCosts);
    }    
    
    private BigDecimal getMoney() {
        return view.getMoney();
    }
    
    private String getItemSelection(){
        return view.getItemSelection();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void getItem(String name, BigDecimal money) throws insufficientFundsException, NoItemInventoryException, vendingMachinePersistenceException {
        item wantedItem = service.getItem(name, money);
        Map<BigDecimal, BigDecimal> changeDuePerCoin = service.getChangePerCoin(wantedItem, money);
        view.displayChangeDuePerCoin(changeDuePerCoin);
        view.displayEnjoyBanner(name);
    }
    

}
