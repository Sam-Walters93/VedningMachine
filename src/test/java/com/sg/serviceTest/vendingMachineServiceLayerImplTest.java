/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.sg.serviceTest;

import com.sg.vendingMachine.dao.vendingMachineAuditDao;
import com.sg.vendingMachine.dao.vendingMachineAuditDaoFileImpl;
import com.sg.vendingMachine.dao.vendingMachineDao;
import com.sg.vendingMachine.dao.vendingMachineDaoFileImpl;
import com.sg.vendingMachine.dao.vendingMachinePersistenceException;
import com.sg.vendingMachine.dto.item;
import com.sg.vendingMachine.service.NoItemInventoryException;
import com.sg.vendingMachine.service.VendingMachineServiceLayer;
import com.sg.vendingMachine.service.VendingMachineServiceLayerImpl;
import com.sg.vendingMachine.service.insufficientFundsException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author stwal
 */
public class vendingMachineServiceLayerImplTest {
    
    vendingMachineDao testDao = new vendingMachineDaoFileImpl("VendingMachineTestFile.txt");  
    String testAuditFile = "testAuditFile.txt";
    vendingMachineAuditDao testAuditDao = new vendingMachineAuditDaoFileImpl(testAuditFile);
    VendingMachineServiceLayer testService = new VendingMachineServiceLayerImpl(testAuditDao,testDao);
    
    
    public vendingMachineServiceLayerImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        
    }
    @Test
    public void testCheckIfEnoughMoney() {
        //ARRANGE
        item twizzlerClone = new item("Twizzlers");
        twizzlerClone.setCost(new BigDecimal("1.60"));
        twizzlerClone.setInventory(9);
        
        BigDecimal enoughMoney = new BigDecimal("2.00");
        BigDecimal notEnoughMoney = new BigDecimal("1.59");
        
        //ACT - enough money
        try {
            testService.checkIfEnoughMoney(twizzlerClone, enoughMoney);
        } catch (insufficientFundsException e){
            fail("There is sufficient funds, exception should not have been thrown");
        }
        //ACT - not enough money
        try {
            testService.checkIfEnoughMoney(twizzlerClone, notEnoughMoney);
            fail("There insufficient funds, exception should have been thrown");
        } catch (insufficientFundsException e){
        }
    }
    
//    @Test
//    public void testGetItemsInStockWithCosts() {
//        
//    }
    
    @Test
    public void testGetChangePerCoin(){
        item kitKatClone = new item("Kitkat");
        kitKatClone.setCost(new BigDecimal("1.60"));
        kitKatClone.setInventory(9);
        
        BigDecimal money = new BigDecimal("2.50");
    
        Map<BigDecimal, BigDecimal> expectedChangePerCoin = new HashMap<>();
        expectedChangePerCoin.put(new BigDecimal("0.25"), new BigDecimal("3"));
        expectedChangePerCoin.put(new BigDecimal("0.10"), new BigDecimal("1"));
        expectedChangePerCoin.put(new BigDecimal("0.05"), new BigDecimal("1"));

        Map<BigDecimal, BigDecimal> changePerCoin = testService.getChangePerCoin(kitKatClone, money);

        assertEquals(changePerCoin.size(), 3, "There should be three coins");
        
    }
    
    @Test
    public void testGetItem() throws insufficientFundsException, vendingMachinePersistenceException, NoItemInventoryException {

        item snickersClone = new item("Snickers");
        snickersClone.setCost(new BigDecimal("2.10"));
        snickersClone.setInventory(0);
        BigDecimal money = new BigDecimal("3.00");
        
        item honeyBunClone = new item("Honey Bun");
        honeyBunClone.setCost(new BigDecimal("2.10"));
        honeyBunClone.setInventory(testDao.getItemInventory("Honey Bun"));
        
        item itemWanted = null;

        try {
            itemWanted = testService.getItem("Snickers", money);
            fail("The item wanted is out of stock.");
        }catch (NoItemInventoryException e) {
        }
        try {
            itemWanted = testService.getItem("Honey Bun", money);
        }catch (NoItemInventoryException e) {
            if (testDao.getItemInventory("Honey Bun")>0){
            fail("The item wanted is in stock.");
        } 

        assertNotNull(itemWanted, "change should not be null");
        assertEquals(itemWanted, honeyBunClone,"The item retrieved should be snickers");
    }
    }
    
    @Test
    public void testRemoveOneItemFromInventory() throws vendingMachinePersistenceException {

        String name = "Snickers";
        
        try{
        
            testService.removeOneItemFromInventory(name);
           
            fail("There are no snickers left, exception should be thrown");
        } catch (NoItemInventoryException e) {  
        }
        
        String honeyBun = "Honey Bun";
        try{
            testService.removeOneItemFromInventory(honeyBun);
        } catch (NoItemInventoryException e) {
            if (testDao.getItemInventory(honeyBun)>0){
                fail("Honey Bun are in stock, exception should not be thrown");
            }
        } 
    }    
}
