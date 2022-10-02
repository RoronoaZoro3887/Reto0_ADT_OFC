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
    /**
    * Crear un nuevo cliente
    * @param cli  Los datos del nuevo cliente
    */
   public void createClient (Client cli);
   /**
    * Obtener los datos del especificado
    * @param id  el id del cliente 
    * @return El cliente
    */
   public Client getDataClient(BigDecimal id);
   /**
    * Obtiene las cuentas de un cliente
    * @param id  El id del cliente
    * @return Devuelve una lista de cuentas
    */
   public Set<Account> getAccountClient(BigDecimal id);
   /**
    * Crear una cuenta a un cliente especifico
    * @param id  El id del cliente
    * @param ac  Los datos de la cuenta
    */
   public void makeAccountClient(BigDecimal id, Account ac);
   /**
    * Añade cliente a una cuenta
    * @param idcuen id de la cuenta
    * @param idcli el id del cliente
    */
   public void addClientAccount(BigDecimal idcuen, BigDecimal idcli);
   /**
    * Obtiene los datos de una cuenta
    * @param id El id de la cuenta
    * @return Devuelve los datos de una cuenta
    */
   public Account getDateAccount(BigDecimal id);
   /**
    * Realiza un movimiento sobre una cuenta especifica
    * @param id El id de la cuenta 
    * @param amount La cantidad
    * @param desc La description
    */
   public void makeMovements(BigDecimal id, Double amount, String desc, Movement mv);
   /**
    * Obtener los datos del movimiento de una cuenta
    * @param id El id de la cuenta
    * @return Devuelve los datos del movimiento
    */
   public Set<Movement> getMovementAccount(BigDecimal id);
   /**
    * Comprobar el id de la cuenta
    * @param idCuenta  El id de la cuenta
    * @return Devuelve los datos de la cuenta
    */
   public boolean comprobarCuenta(BigDecimal idCuenta);
   /**
    * Comprobar el id del cliente
    * @param idCliente  El id del cliente
    * @return Devuelve los datos de la cliente
    */
   public boolean comprobarCliente(BigDecimal idCliente);
   
   
       
   
    
}
