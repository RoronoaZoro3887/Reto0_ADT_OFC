/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.util.Set;

/**
 *
 * @author 2dam
 */
public class Account {
    
    private Integer id;
    private String desc;
    private  Float balance;
    private Float creditLine;
    private Float beginBalance;
    private LocalDate bBTs;
    private AccountType type;
    private Set<Movement> movementList;

    public Account() {
        
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

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Float getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(Float creditLine) {
        this.creditLine = creditLine;
    }

    public Float getBeginBalance() {
        return beginBalance;
    }

    public void setBeginBalance(Float beginBalance) {
        this.beginBalance = beginBalance;
    }

    public LocalDate getbBTs() {
        return bBTs;
    }

    public void setbBTs(LocalDate bBTs) {
        this.bBTs = bBTs;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Set<Movement> getMovementList() {
        return movementList;
    }

    public void setMovementList(Set<Movement> movementList) {
        this.movementList = movementList;
    }
    
    
    
    
}
