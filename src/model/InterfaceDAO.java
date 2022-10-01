/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Set;

/**
 *
 * @author 2dam
 */
public interface InterfaceDAO {
    
   public void createClient (Client cli);
   public Client getDataClient(Integer id);
   public Set<Account> getAccountClient(Integer id);
   public void makeAccountClient(Integer id, Account ac);
   public void addClientAccount(Integer idcuen, Integer idcli);
   public Account getDateAccount(Integer id);
   public void makeMovements(Integer id);
   public Movement getMovementAccount(Integer id);
       
   
    
}
