/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.sg.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author stwal
 */
public class vendingMachineAuditDaoFileImpl implements vendingMachineAuditDao {

    private final String AUDIT_FILE;
   
    public vendingMachineAuditDaoFileImpl() {
        this.AUDIT_FILE = "audit.txt";
    }
   
    public vendingMachineAuditDaoFileImpl(String auditTestFile) {
        this.AUDIT_FILE = auditTestFile;
    }
    

    @Override
    public void writeAuditEntry(String entry) throws vendingMachinePersistenceException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new vendingMachinePersistenceException("Could not persist audit information", e);
        }
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " +entry);
        out.flush();
    }
}
