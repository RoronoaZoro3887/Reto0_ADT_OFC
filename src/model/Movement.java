/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Scanner;
import utilidades.Util;

/**
 *
 * @author 2dam
 */
public class Movement {
    Scanner sc = new Scanner(System.in);
    
    private Integer id;
    private String desc;
    private Double balance;
    private Double amount;
    private Timestamp bBTs;
    private BigDecimal accountId;

    public Movement() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getBalance() {
        return balance;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Timestamp getbBTs() {
        return bBTs;
    }

    public void setbBTs(Timestamp bBTs) {
        this.bBTs = bBTs;
    }

    public BigDecimal getAccountId() {
        return accountId;
    }

    public void setAccountId(BigDecimal accountId) {
        this.accountId = accountId;
    }

    public void getDatos() {
        System.out.println("ID del movimiento: "+id);
        System.out.println("Descripcion: "+desc);
        System.out.println("Saldo: "+balance);
        System.out.println("Cantidad: "+amount);
        System.out.println("Fecha de movimiento: "+bBTs);
        System.out.println("ID de la cuenta: "+accountId);
        System.out.println(" ");
    }
    
    public void setDatosBD() {
        
        
    }

    public void setDatos() {
        
    }
    
    
    
}
