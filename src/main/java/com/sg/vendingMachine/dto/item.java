/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.sg.vendingMachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author stwal
 */
public class item {
    
    
    private String name;
    private BigDecimal cost;
    private int inventory; 
    

    public item(String name, String cost, int inventory) {
        this.name = name;
        this.cost = new BigDecimal(cost);
        this.inventory = inventory;
    }
    
    public item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.cost);
        hash = 31 * hash + this.inventory;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final item other = (item) obj;
        if (this.inventory != other.inventory) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", cost=" + cost + ", inventory=" + inventory + '}';
    }
   
}
