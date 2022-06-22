/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.sg.dao;
import com.sg.vendingMachine.dao.vendingMachineAuditDaoFileImpl;
import com.sg.vendingMachine.dao.vendingMachineAuditDao;
import com.sg.vendingMachine.dao.vendingMachinePersistenceException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author stwal
 */
public class vendingMachineAuditDaoFileImplTest { 
    String testAuditFile = "testAuditFile.txt";
    vendingMachineAuditDao testAuditDao = new vendingMachineAuditDaoFileImpl(testAuditFile);
    
    
    public vendingMachineAuditDaoFileImplTest() {
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
    public void testWriteAuditEntry() throws vendingMachinePersistenceException {
   
        String entry = "One Snickers removed.";
        
        testAuditDao.writeAuditEntry(entry);
        
    }
    
}
