/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import utilidades.Util;

/**
 *
 * @author 2dam
 */
public class Movement implements Serializable {

    Scanner sc = new Scanner(System.in);

    private Integer id;
    private String desc;
    private Double balance;
    private Double amount;
    private Timestamp bBTs;
    private LocalDate bBTsf;
    private BigDecimal accountId;
    private static final AtomicInteger movementId = new AtomicInteger(0);
    private Integer IdIncremet;

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
        System.out.println("--------------ID Movimiento " + this.id + "----------------------");
        System.out.println(" ");
        System.out.println("Descripcion: " + desc);
        System.out.println("Saldo: " + balance);
        System.out.println("Cantidad: " + amount);
        System.out.println("Fecha de movimiento: " + bBTs + "" + bBTsf);
        System.out.println("ID de la cuenta: " + accountId);
        System.out.println(" ");
        System.out.println("----------------------------------------------------------");

    }

    public void setDatosBD() {

    }

    public void setDatos() {
        IdIncremet = movementId.incrementAndGet();
        this.id = IdIncremet;
        this.desc = Util.introducirCadena("Escribe el desc");
        System.out.println("Introduce el balance");
        this.balance = Util.leerDouble();
        System.out.println("Introduce el amount");
        this.amount = Util.leerDouble();
        this.bBTsf = Util.leerFecha("Escribe la fecha");
        this.accountId = BigDecimal.valueOf(Util.leerInt("Escribe id de la cuenta que realizara el movimiento"));
    }

}
