/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author chaseowens
 */
public class PurchaseOrder {
    String orderNumber, customerName, productType, state;
    BigDecimal area, materialCost, laborCost, tax, total, materialCostPerSquareFoot, laborCostPerSquareFoot, subTotal, taxRate;
    LocalDate date;
    
    public PurchaseOrder() {
        
    }
    
    public PurchaseOrder(String name, Product product, StateTax stateTax, String area, LocalDate date) {
        this.customerName = name;
        this.area = new BigDecimal(area);
        this.productType = product.getProduct();
        this.state = stateTax.getState();
        this.taxRate = stateTax.getTaxRate();
        this.materialCostPerSquareFoot = product.getCostPerSquareFoot();
        this.laborCostPerSquareFoot = product.getLaborPerSquareFoot();
        this.laborCost = this.laborCostPerSquareFoot.multiply(this.area);
        this.materialCost = this.materialCostPerSquareFoot.multiply(this.area);
        this.subTotal = this.laborCost.add(this.materialCost);
        this.tax = (this.subTotal.multiply(this.taxRate)).divide(new BigDecimal("100"));
        this.total = this.subTotal.add(tax);
        this.date = date;
    }
    
    public PurchaseOrder(String orderNumber, String customerName, String state, String taxRate, String productType, String area, String materialCostPerSquareFoot, String laborCostPerSquareFoot, String materialCost, String laborCost, String tax, String total) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.state = state;
        this.taxRate = new BigDecimal(taxRate);
        this.productType = productType;
        this.area = new BigDecimal(area);
        this.materialCostPerSquareFoot = new BigDecimal(materialCostPerSquareFoot);
        this.laborCostPerSquareFoot = new BigDecimal(laborCostPerSquareFoot);
        this.materialCost = new BigDecimal(materialCost);
        this.laborCost = new BigDecimal(laborCost);
        this.tax = new BigDecimal(tax);
        this.total = new BigDecimal(total);
    }
    
    @Override
    public String toString() {
        return this.orderNumber + " : " + this.customerName + " : " + this.state + " : " + this.taxRate + " : " + this.productType + " : " + this.area + " : " + this.materialCostPerSquareFoot + " : " + this.laborCostPerSquareFoot + " : " + this.materialCost + " : " + this.laborCost + " : " + this.subTotal + " : " + this.tax + " : " + this.total;
    }
    
    // update laborCost, materialCost, tax, total
    public void updatePODetails() {
        this.laborCost = this.laborCostPerSquareFoot.multiply(this.area);
        this.materialCost = this.materialCostPerSquareFoot.multiply(this.area);
        this.subTotal = this.laborCost.add(this.materialCost);
        this.tax = (this.subTotal.multiply(this.taxRate)).divide(new BigDecimal("100"));
        this.total = this.subTotal.add(tax);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getMaterialCostPerSquareFoot() {
        return materialCostPerSquareFoot;
    }

    public void setMaterialCostPerSquareFoot(BigDecimal materialCostPerSquareFoot) {
        this.materialCostPerSquareFoot = materialCostPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.orderNumber);
        hash = 47 * hash + Objects.hashCode(this.customerName);
        hash = 47 * hash + Objects.hashCode(this.productType);
        hash = 47 * hash + Objects.hashCode(this.state);
        hash = 47 * hash + Objects.hashCode(this.area);
        hash = 47 * hash + Objects.hashCode(this.materialCost);
        hash = 47 * hash + Objects.hashCode(this.laborCost);
        hash = 47 * hash + Objects.hashCode(this.tax);
        hash = 47 * hash + Objects.hashCode(this.total);
        hash = 47 * hash + Objects.hashCode(this.materialCostPerSquareFoot);
        hash = 47 * hash + Objects.hashCode(this.laborCostPerSquareFoot);
        hash = 47 * hash + Objects.hashCode(this.subTotal);
        hash = 47 * hash + Objects.hashCode(this.taxRate);
        hash = 47 * hash + Objects.hashCode(this.date);
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
        final PurchaseOrder other = (PurchaseOrder) obj;
        if (!Objects.equals(this.orderNumber, other.orderNumber)) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        if (!Objects.equals(this.total, other.total)) {
            return false;
        }
        if (!Objects.equals(this.materialCostPerSquareFoot, other.materialCostPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.laborCostPerSquareFoot, other.laborCostPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.subTotal, other.subTotal)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
    
}
