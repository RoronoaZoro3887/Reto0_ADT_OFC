/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author 2dam
 */
public class Movement {
    
    private Integer id;
    private String desc;
    private Double balance;
    private Double amount;
    private LocalDate bBTs;
    private Integer accountId;

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

    public LocalDate getbBTs() {
        return bBTs;
    }

    public void setbBTs(LocalDate bBTs) {
        this.bBTs = bBTs;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
    
    
    
}
