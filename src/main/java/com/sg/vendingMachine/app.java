/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.sg.vendingMachine;


import com.sg.vendingMachine.controller.vendingMachineController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author stwal
 */
public class app {

    /**
     * @param args the command line arguments
     */
       public static void main(String[] args) {
//        userIO io = new userIOConsoleImpl();
//        vendingMachineView view = new vendingMachineView(io);
//        vendingMachineAuditDao auditDao = new vendingMachineAuditDaoFileImpl();
//        vendingMachineDao dao = new vendingMachineDaoFileImpl();
//        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(auditDao, dao);
//        
//        vendingMachineController controller = new vendingMachineController(view, service);
//        
//        controller.run();
//
//        ApplicationContext appContext
//            = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//
//        vendingMachineController controller = appContext.getBean("controller", vendingMachineController.class);
//        controller.run();

        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//            appContext.scan("com.sg.vendingMachine");
//            appContext.refresh();
        

        vendingMachineController controller = appContext.getBean("controller", vendingMachineController.class);
        
        controller.run();

    }
}
