/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;
import utilidades.Util;

/**
 *
 * @author 2dam
 */
public class Account {
    
    private BigDecimal id;
    private String desc;
    private Double balance;
    private Double creditLine;
    private Double beginBalance;
    private Timestamp bBTs;
    private AccountType type;
    private Set<Movement> movementList;
    

    public Account() {
        
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

    public Double getBeginBalance() {
        return beginBalance;
    }

    public void setBeginBalance(Double beginBalance) {
        this.beginBalance = beginBalance;
    }

    public Timestamp getbBTs() {
        return bBTs;
    }

    public void setbBTs(Timestamp bBTs) {
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

    public void getDatos() {
        System.out.println("ID  de la cuenta "+id);
        System.out.println("Saldo "+balance);
        System.out.println("Saldo inicial "+beginBalance);
        System.out.println("Fecha saldo inicial "+bBTs);
        System.out.println("Linea de credito "+creditLine);
        System.out.println("Descripcion: "+desc);
        System.out.println("Tipo: "+type);
        System.out.println("Movimientos: ");
        System.out.println(movementList.size());
        if(movementList!=null){
            for (Movement movement : movementList) {
                movement.getDatos();
            }
            
        }else{
            System.out.println("No tiene movimientos");
        }
        System.out.println(" ");
        
    }

    public void setDatosBD() {
        
        System.out.println("Introduce el sueldo inicial: ");
        beginBalance = Util.leerDouble();
        balance = beginBalance;
        System.out.println("Introduce la linea de credito: ");
        creditLine = Util.leerDouble();
        desc = Util.introducirCadena("Introduce la descripcion de la cuenta: ");
        System.out.println("Elige entre tipo Standard(S) o Credito(C)");
        char elegir = Util.leerChar('S', 'C');
        if(elegir =='S'){
           type= AccountType.STANDAR;
        }else if(elegir =='C'){
            type = AccountType.CREDIT;
        }
        
        
    }
    
    
    
    
}
