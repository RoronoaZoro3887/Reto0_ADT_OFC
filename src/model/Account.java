/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import utilidades.Util;

/**
 *
 * @author 2dam
 */
public class Account implements Serializable {

    private Integer id;
    private String desc;
    private Double balance;
    private Double creditLine;
    private LocalDate bBTs;
    private AccountType type;
    private Set<Movement> movementList;
    private static final AtomicInteger accountId = new AtomicInteger(0); 
    private Integer IdIncremet ;
    

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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(Double creditLine) {
        this.creditLine = creditLine;
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

    public void setDatos() {
        IdIncremet= accountId.incrementAndGet();
        this.id = IdIncremet;
        this.desc = Util.introducirCadena("Escribe el desc");
        System.out.println("Introduce el balance");
        this.balance = Util.leerDouble();
         System.out.println("Escribe los creditLine");
        this.creditLine = Util.leerDouble();
        
        this.bBTs = Util.leerFecha("Escribe la fecha");
        int respu = Util.leerInt("Escribe tipo de cuenta  0 = Standar "
                + " 1 = Credit");
        if (respu == 0) {
            this.type = AccountType.STANDAR;
        } else if (respu == 1) {
            this.type = AccountType.CREDIT;
        } else {
            System.out.println("No es ni 1 ni 2");
        }
    }

    public void getDatos() {
        

        System.out.println("-----------------ID Cuentas "+this.id+"----------------------");
        System.out.println("ID de la cuenta " + this.id);
        System.out.println("Descripcion de la cuenta" + this.desc);
        System.out.println("Balance de la cuenta:" + this.balance);
        System.out.println("Linea de credito:" + this.creditLine);
        System.out.println("Fecha de creacion:" + this.bBTs);
        System.out.println("Tipo de cuenta:" + this.type);

        for (Movement M : movementList) {
            System.out.println(M.getId());
        }
        System.out.println("----------------------------------------------------------");
    }
  public void addMovement(Movement mov) {

        this.movementList.add(mov);

    }

    
}
