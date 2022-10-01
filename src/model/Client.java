/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import utilidades.Util;

/**
 *
 * @author 2dam
 */
public class Client implements Serializable  {
    private Integer id;
    private String firstName;
    private String lastName;
    private String middleIntial;
    private String street;
    private String city;
    private String state;
    private Integer zip;
    private Integer phone;
    private String email;
    private Set<Account> accountList;

    public Client() {
        accountList = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleIntial() {
        return middleIntial;
    }

    public void setMiddleIntial(String middleIntial) {
        this.middleIntial = middleIntial;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(Set<Account> accountList) {
        this.accountList = accountList;
    }
    
    public void crearCuentaCliente (){
       String Correo = null;
       Correo = Util.introducirCadena("Escribe el correo del cliente");
      
       Account acc = new Account();
       acc.setDatos();
       accountList.add(acc);
    }
    public void setDatos(){     
         this.id = Util.leerInt("Escribe el id");
        this.firstName = Util.introducirCadena("Escribe el nombre");
        this.middleIntial = Util.introducirCadena("Introduce el middleIntial");
        this.lastName = Util.introducirCadena("Escribe los apellidos");
        this.street = Util.introducirCadena("Escribe la dirección");     
        this.city = Util.introducirCadena("Escribe la ciudad");
        this.state = Util.introducirCadena("Escribe el estado");
        this.zip = Util.leerInt("Introduce el codigo postal");
        this.phone = Util.leerInt("Introduce el Telefono");
        this.email = Util.introducirCadena("Escribe el Correo Electronico");  
        
    } 
    public void getDatos(){
            System.out.println("Id de la cuenta" + this.id);
            System.out.println("Nombre de Usuario:" + this.firstName);
            System.out.println("middleIntial de Usuario:" + this.middleIntial);
            System.out.println("lastName de Usuario:" + this.lastName);
            System.out.println("street de Usuario:" + this.street);
            System.out.println("city de Usuario:" + this.city);
            System.out.println("state de Usuario:" + this.state);
            System.out.println("zip de Usuario:" + this.zip);
            System.out.println("phone de Usuario:" + this.phone);
            System.out.println("email de Usuario:" + this.email);
            System.out.println("Cuentas del cliente:");
            
            listaCuentas();
    }

    private void listaCuentas() {
             for(Account A : accountList){
                A.getDatos();
        }
    }
}