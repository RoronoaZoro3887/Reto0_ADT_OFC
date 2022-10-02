/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.util.Set;

/**
 *
 * @author 2dam
 */
public interface InterfaceDAO {
    
   public void createClient (Client cli);
   public Client getDataClient(BigDecimal id);
   public Set<Account> getAccountClient(BigDecimal id);
   public void makeAccountClient(BigDecimal id, Account ac);
   public void addClientAccount(BigDecimal idcuen, BigDecimal idcli);
   public Account getDateAccount(BigDecimal id);
   public void makeMovements(BigDecimal id, Double amount, String desc, Movement mov);
   public Set<Movement> getMovementAccount(BigDecimal id);
       
   
    
}
