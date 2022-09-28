/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import utilidades.Util;

/**
 *
 * @author 2dam
 */
public class Account{
    
    private Integer id;
    private String desc;
    private Float balance;
    private Float creditLine;
    private Float beginBalance;
    private LocalDate bBTs;
    private AccountType type;
    private Set<Movement> movementList;
   
    public Account() {
        movementList = new HashSet<>();
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
    
     public void setDatos(){     
         
        this.desc = Util.introducirCadena("Escribe el desc");
        this.balance = Util.leerFloat("Introduce el balance");
        this.creditLine = Util.leerFloat("Escribe los creditLine");
        this.beginBalance = Util.leerFloat("Escribe la beginBalance");     
        this.bBTs = Util.leerFecha("Escribe la fecha");
         System.out.println("Escribe tipo de cuenta /n 0 = Standar /n 1 = Credit");
        if(Util.esBoolean()){
        this.type = AccountType.STANDAR;
                   this.type = AccountType.CREDIT;
        }
        
    }
   public void getDatos(){
   
            System.out.println("Descripcion de la cuenta" + this.desc);
            System.out.println("Balance de la cuenta:" + this.balance);
            System.out.println("Linea de credito:" + this.creditLine);
            System.out.println("Balance inicial:" + this.beginBalance);
            System.out.println("Fecha de creacion:" + this.bBTs);
            System.out.println("Tipo de cuenta:" + this.type);
           
          for(Movement M : movementList){
                System.out.println(M.getId());
        }
    }
    
    
}
