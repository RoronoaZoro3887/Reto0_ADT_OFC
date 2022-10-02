/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;
import utilidades.Util;

/**
 *
 * @author 2dam
 */
public class Movement implements Serializable {

    private BigDecimal id;
    private String desc;
    private Double balance; // dinero que tienes
    private Double amount; // dinero que tienes
    private LocalDate bBTs;
    private BigDecimal accountId;
    private static final AtomicInteger movementId = new AtomicInteger(0);
    private BigDecimal IdIncremet;

    public Movement() {

    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {
        return balance;
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

    public BigDecimal getAccountId() {
        return accountId;
    }

    public void setAccountId(BigDecimal accountId) {
        this.accountId = accountId;
    }

    public void setDatos() {
        IdIncremet = BigDecimal.valueOf(movementId.incrementAndGet());
        this.id = IdIncremet;
        this.desc = Util.introducirCadena("Escribe el desc");
        System.out.println("Introduce el balance");
        this.balance = Util.leerDouble();
        System.out.println("Introduce el amount");
        this.amount = Util.leerDouble();
        this.bBTs = Util.leerFecha("Escribe la fecha");
        this.accountId = BigDecimal.valueOf(Util.leerInt("Escribe id de la cuenta que realizara el movimiento"));

    }

    public void getDatos() {
        System.out.println("--------------ID Movimiento " + this.id + "----------------------");
        System.out.println("Descripcion del movimiento:" + this.desc);
        System.out.println("Balance del movimiento:" + this.balance);
        System.out.println("Balance del amount:" + this.amount);
        System.out.println("Fecha del movimiento:" + this.bBTs);
        System.out.println("Cuenta que realizo el movimiento:" + this.accountId);
        System.out.println("----------------------------------------------------------");

    }
}
