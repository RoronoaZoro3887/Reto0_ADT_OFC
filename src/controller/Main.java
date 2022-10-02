/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Set;
import model.Account;
import model.Client;
import model.ImplementBD;
import model.ImplementFich;
import model.InterfaceDAO;
import model.Movement;
import utilidades.Util;

/**
 *
 * @author 2dam
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int opc = 0;

        
        System.out.println("Elige en que quieres guardar la información Ficheros= 1 o BD=2");
        int eleccion = Util.leerInt(1, 2);
            
        do {
            opc = Menu();
            
            InterfaceDAO dao;
            if (eleccion == 1) {
                dao= new ImplementFich();
            } else {
                 dao= new ImplementBD();
            }

            switch (opc) {

                case 1:
                    System.out.println("A continuación procederemos a solicitar los datos del cliente /n a crear");
                    crearCliente(dao);
                    
                    // if decidir que forma tratar dato (bd o ficheros)
                    break;
                case 2:
                    consultarDatosDeUnCliente(eleccion, dao, sc);
                    break;
                case 3:
                    consultarCuentasCliente(eleccion, dao, sc);
                    break;
                case 4:
                    crearCuentasCliente(dao);
                    break;
                case 5:
                    agregarClienteCuenta(dao, sc);
                    break;
                case 6:
                    consultarDatosCuenta(eleccion, dao, sc);
                    break;
                case 7:
                    realizarMovimientoCuenta(eleccion, dao, sc);
                    break;
                case 8:
                    consultarMovimientos(eleccion, dao, sc);
                    break;

            }
        } while (opc!=9);

    }

    private static int Menu() {

        int opc;

        System.out.println("1.Crear Cliente");
        System.out.println("2.Consultar Datos Cliente");
        System.out.println("3.Consultar Cuentas de un Cliente");
        System.out.println("4.Crear Cuenta para Cliente");
        System.out.println("5. Agregar cliente cuenta");
        System.out.println("6.Consultar Datos de una Cuenta");
        System.out.println("7.Realizar Movimiento Sobre una Cuenta");
        System.out.println("8.Consultar Movimientos de una Cuenta");

        opc = Util.leerInt("Escoge una opcion");
        return opc;
    }

    private static void crearCliente(InterfaceDAO dao) {
        Client cli = new Client();
        char seguir = ' ';

        do{
            cli.setDatosBD();
            dao.createClient(cli);
            System.out.println("Quieres seguir introduciendo clientes? (S = si, N = no)");
            seguir = Util.leerChar('N', 'S');
        }while(seguir != 'N');
        
        
        
    }

    private static void consultarDatosDeUnCliente(int eleccion, InterfaceDAO dao, Scanner sc) {
        BigDecimal id = null;
        
        System.out.println("Introduce el id de la cuenta");
        id = sc.nextBigDecimal();
        if(eleccion == 2){
           while(!dao.comprobarCliente(id)){
               System.out.println(" El id no existe, introduce otro: ");
               id = sc.nextBigDecimal();
               
           }
        }
        dao.getDataClient(id).getDatos();
        
        
        
    }

    private static void consultarCuentasCliente(int eleccion, InterfaceDAO dao, Scanner sc) {
        BigDecimal id = null;

        System.out.println("Introduce el id del cliente");
        id = sc.nextBigDecimal();
        if(eleccion == 2){
           while(!dao.comprobarCliente(id)){
               System.out.println(" El id no existe, introduce otro: ");
               id = sc.nextBigDecimal();
               
           }
        }
        Set<Account> ac = dao.getAccountClient(id);
        for (Account account : ac) {
            account.getDatos();
        }
        
    }

    private static void crearCuentasCliente(InterfaceDAO dao) {
        BigDecimal id2 = null;
        Account ac = new Account();
        Integer id = Util.leerInt("Inserta el id del cliente");
        char seguir = ' ';
        id2 = BigDecimal.valueOf(id);
        
        do{
            while (!dao.comprobarCliente(id2)) {
                id = Util.leerInt("El id no existe, inserta otro id Cliente");
            }
            id2 = BigDecimal.valueOf(id);
            ac.setDatosBD();
            dao.makeAccountClient(id2, ac);

            System.out.println("Quieres seguir introduciendo cuentas a este cliente? (S = si, N = no)");
            seguir = Util.leerChar('N', 'S');
        } while (seguir != 'N');
        
    }

    private static void agregarClienteCuenta(InterfaceDAO dao, Scanner sc) {
        BigDecimal id = null;
        BigDecimal id2 = null;
        
        System.out.println("Introduce el id del cliente");
        id = sc.nextBigDecimal();
        while(!dao.comprobarCliente(id)){
               System.out.println(" El id no existe, introduce otro: ");
               id = sc.nextBigDecimal();
               
           }
        System.out.println("Introduce el id de la Cuenta");
        id = sc.nextBigDecimal();
        while(!dao.comprobarCuenta(id2)){
               System.out.println(" El id no existe, introduce otro: ");
               id2 = sc.nextBigDecimal();
               
           }
        dao.addClientAccount(id, id2);
    }

    private static void realizarMovimientoCuenta(int eleccion, InterfaceDAO dao, Scanner sc) {
        BigDecimal idCuenta = null;
        String desc = null;
        Double amount = null;
        Movement mv = new Movement();
        if (eleccion == 2) {
            System.out.println("Introduce el Id  de la cuenta: ");
            idCuenta = sc.nextBigDecimal();
            
            System.out.println("Introduce la cantidad");
            amount = Util.leerDouble();
            while (!dao.comprobarCuenta(idCuenta)) {
                System.out.println(" El id no existe, introduce otro: ");
                idCuenta = sc.nextBigDecimal();
            }
            Integer descripcion = Util.leerInt("Introduce la descripcion del movimiento (Deposit = 1, Payment = 0)", -1, 2);
            if (descripcion == 1) {
                desc = "Deposit";
            } else {
                desc = "Payment";
            }
            mv.setDatosBD();
        }else{
            mv.setDatos();
        }
        
        dao.makeMovements(idCuenta, amount, desc, mv);
        
        
    }

    private static void consultarMovimientos(int eleccion, InterfaceDAO dao, Scanner sc) {
        BigDecimal id = null;
        
        System.out.println("Introduce el id de una cuenta: ");
        id = sc.nextBigDecimal();
        if(eleccion == 2){
           while(!dao.comprobarCuenta(id)){
               System.out.println(" El id no existe, introduce otro: ");
               id = sc.nextBigDecimal();
               
           }
        }
        Set<Movement> mv = dao.getMovementAccount(id);
        for (Movement movement : mv) {
            movement.getDatos();
        }
    }

    private static void consultarDatosCuenta(int eleccion, InterfaceDAO dao, Scanner sc) {
       BigDecimal id = null;
       System.out.println("Introduce el id de una cuenta: ");
        id = sc.nextBigDecimal();
        if(eleccion == 2){
           while(!dao.comprobarCuenta(id)){
               System.out.println(" El id no existe, introduce otro: ");
               id = sc.nextBigDecimal();
               
           }
        }
        dao.getDateAccount(id).getDatos();
    }

}
