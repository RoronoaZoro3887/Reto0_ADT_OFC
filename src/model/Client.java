/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import utilidades.Util;

/**
 *
 * @author 2dam
 */
public class Client implements Serializable {

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
    private static final AtomicInteger clientId = new AtomicInteger(0);
    private BigDecimal IdIncremet;

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
        System.out.println("-----------------ID Cliente: " + this.id + "----------------------");
        System.out.println(" ");

        System.out.println("Ciudad: " + city);
        System.out.println("Email: " + email);
        System.out.println("Calle: " + street);
        System.out.println("nombre: " + firstName);
        System.out.println("Apellido: " + lastName);
        System.out.println("Middle initial: " + middleIntial);
        System.out.println("Telefono: " + phone);
        System.out.println("Estado: " + state);
        System.out.println("Codigo postal: " + zip);
        System.out.println(" ");
        System.out.println("Cuentas: ");
        if (!accountList.isEmpty()) {
            listaCuentas();
        } else {
            System.out.println("No tiene cuentas");
        }
        System.out.println(" ");
        System.out.println("----------------------------------------------------------");

    }

    public void setDatosBD() {
        city = Util.introducirCadena("Introduce la ciudad: ");
        email = Util.introducirCadena("Introduce el email: ");
        street = Util.introducirCadena("Introduce la calle: ");
        firstName = Util.introducirCadena("Introduce el nombre: ");
        lastName = Util.introducirCadena("Introduce el apellido");
        middleIntial = lastName.charAt(0) + ".";
        phone = BigDecimal.valueOf(Util.leerInt("Introduce el telefono"));
        state = Util.introducirCadena("Introduce el estado: ");
        int n;
        int cifra = 0;
        int num;
        do {
            n = Util.leerInt("Introduce el codigo postal: (5 numeros)");
            num = n;
            while (n != 0) {
                n = n / 10;
                cifra++;
            }
        } while (cifra != 5);
        zip = num;

    }

    public void setDatos() {
        IdIncremet = BigDecimal.valueOf(clientId.incrementAndGet());
        this.id = IdIncremet;
        this.firstName = Util.introducirCadena("Escribe el nombre");
        this.middleIntial = Util.introducirCadena("Introduce el middleIntial");
        this.lastName = Util.introducirCadena("Escribe los apellidos");
        this.street = Util.introducirCadena("Escribe la dirección");
        this.city = Util.introducirCadena("Escribe la ciudad");
        this.state = Util.introducirCadena("Escribe el estado");
        this.zip = Util.leerInt("Introduce el codigo postal");
        this.phone = BigDecimal.valueOf(Util.leerInt("Introduce el Telefono"));
        this.email = Util.introducirCadena("Escribe el Correo Electronico");

    }

    private void listaCuentas() {
        for (Account A : accountList) {
            A.getDatos();
        }
    }

    public void addAccount(Account ac) {

        this.accountList.add(ac);

    }

}
