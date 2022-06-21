/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.sg.vendingMachine;

import com.sg.controller.vendingMachineController;
import com.sg.dao.vendingMachineAuditDao;
import com.sg.dao.vendingMachineAuditDaoFileImpl;
import com.sg.dao.vendingMachineDao;
import com.sg.dao.vendingMachineDaoFileImpl;
import com.sg.service.VendingMachineServiceLayer;
import com.sg.service.VendingMachineServiceLayerImpl;
import com.sg.ui.userIO;
import com.sg.ui.userIOConsoleImpl;
import com.sg.ui.vendingMachineView;

/**
 *
 * @author stwal
 */
public class app {

    /**
     * @param args the command line arguments
     */
       public static void main(String[] args) {
        userIO io = new userIOConsoleImpl();
        vendingMachineView view = new vendingMachineView(io);
        vendingMachineAuditDao auditDao = new vendingMachineAuditDaoFileImpl();
        vendingMachineDao dao = new vendingMachineDaoFileImpl();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(auditDao, dao);
        
        vendingMachineController controller = new vendingMachineController(view, service);
        
        controller.run();
    }
}
