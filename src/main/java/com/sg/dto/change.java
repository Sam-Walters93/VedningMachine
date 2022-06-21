/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.sg.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author stwal
 */
public class change {
    
    public static BigDecimal changeDueInPennies (BigDecimal itemCost, BigDecimal money) {
        BigDecimal changeDueInPennies = (money.subtract(itemCost)).multiply(new BigDecimal("100"));
        System.out.println("Change due: $" + (changeDueInPennies.divide(new BigDecimal("100"),2,RoundingMode.HALF_UP).toString()));
        return changeDueInPennies;
    }
    
    public static Map<BigDecimal, BigDecimal> changeDuePerCoin (BigDecimal itemCost, BigDecimal money) {
        coin[] coinEnumArray = coin.values();
        ArrayList <String> coinStringList = new ArrayList<>();
        for (coin coin : coinEnumArray) {
            coinStringList.add(coin.toString());
        }

          ArrayList<BigDecimal> coins = new ArrayList<BigDecimal>(); 
          for (String coin:coinStringList) {
              coins.add(new BigDecimal(coin));
          }
          
        BigDecimal changeDueInPennies = changeDueInPennies(itemCost, money);
       
        BigDecimal noOfCoin;
        BigDecimal zero = new BigDecimal("0");
      
        Map <BigDecimal, BigDecimal> amountPerCoin = new HashMap<>();
        
        
        for (BigDecimal coin : coins) {
          
            if (changeDueInPennies.compareTo(coin) >= 0) {
                
                if (!changeDueInPennies.remainder(coin).equals(zero)) {
                   
                    noOfCoin = changeDueInPennies.divide(coin,0,RoundingMode.DOWN);
              
                    amountPerCoin.put(coin, noOfCoin);
                
                    changeDueInPennies = changeDueInPennies.remainder(coin);
                  
                    if (changeDueInPennies.compareTo(zero)<0) {  
                        break;
                    }
               
                } else if (changeDueInPennies.remainder(coin).equals(zero)) {  
                    noOfCoin = changeDueInPennies.divide(coin,0,RoundingMode.DOWN);
                    amountPerCoin.put(coin, noOfCoin);
                   
                    if ((changeDueInPennies.compareTo(zero))<0) {
                        break;
                    }
                }
            } else {
                ;  
            }
        }
        return amountPerCoin;
    }   
}
