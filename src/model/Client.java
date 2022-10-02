/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import utilidades.Util;

/**
 *
 * @author 2dam
 */
public class Client {
    private BigDecimal id;
    private String firstName;
    private String lastName;
    private String middleIntial;
    private String street;
    private String city;
    private String state;
    private Integer zip;
    private BigDecimal phone;
    private String email;
    private Set<Account> accountList;

    public Client() {
        accountList = new HashSet<>();
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
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

    public BigDecimal getPhone() {
        return phone;
    }

    public void setPhone(BigDecimal phone) {
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

    public void getDatos() {
        System.out.println("ID del cliente: "+ id);
        System.out.println("Ciudad: "+ city);
        System.out.println("Email: "+ email);
        System.out.println("Calle: "+ street);
        System.out.println("nombre: "+ firstName);
        System.out.println("Apellido: "+ lastName);
        System.out.println("Middle initial: "+ middleIntial);
        System.out.println("Telefono: "+ phone);
        System.out.println("Estado: "+ state);
        System.out.println("Codigo postal: "+ zip);
        System.out.println(" ");
        System.out.println("Cuentas: ");
        if (!accountList.isEmpty()) {
            for (Account account : accountList) {
                account.getDatos();
            }
        }else{
            System.out.println("No tiene cuentas");
        }
        
        
        
    }

    public void setDatosBD() {
        city = Util.introducirCadena("Introduce la ciudad: ");
        email = Util.introducirCadena("Introduce el email: ");
        street = Util.introducirCadena("Introduce la calle: ");
        firstName = Util.introducirCadena("Introduce el nombre: ");
        lastName = Util.introducirCadena("Introduce el apellido");
        middleIntial = lastName.charAt(0)+".";
        phone = BigDecimal.valueOf(Util.leerInt("Introduce el telefono"));
        state = Util.introducirCadena("Introduce el estado: ");
        int n; 
        int cifra = 0;
        int num;
        do{
            n = Util.leerInt("Introduce el codigo postal: (5 numeros)");
            num = n;
            while (n != 0) {
                n = n / 10;
                cifra++;
            }
        }while(cifra!=5);
        zip = num;
        
                
             
        
    }
    
    
}
