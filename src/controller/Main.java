/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Account;
import model.Client;
import model.ImplementBD;
import model.ImplementFich;
import model.InterfaceDAO;
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
        int opc = 0;
        Client c;
        Account A;

        int eleccion;
        do {
            System.out.println("Elige en que quieres guardar"
                    + " la información Ficheros= 1 o BD=2");

            eleccion = Util.leerInt();
        } while (eleccion != 1 && eleccion != 2);

        do {
            opc = Menu();
            InterfaceDAO dao;
            if (eleccion == 1) {
                dao = new ImplementFich();
            } else {
                dao = new ImplementBD();
            }

            switch (opc) {

                case 1:
                    c = new Client();
                    c.setDatos();
                    dao.createClient(c);
                    break;
                case 2:

                    Integer i = Util.leerInt("Introduce el id del "
                            + "cliente a buscar");
                    c = dao.getDataClient(i);

                    c.getDatos();
                    break;
                case 3:
                    Set<Account> cuentasCli = new HashSet<>();
                    Integer idCuentaCli = Util.leerInt("Introduce el id del usuario"
                            + "a consultar cuentas");
                    cuentasCli = dao.getAccountClient(idCuentaCli);

                    for (Account Alist : cuentasCli) {
                        Alist.getDatos();
                        break;
                    }
                case 4:
                    Integer idMakeCli = Util.leerInt("Introduce el id del usuario"
                            + "al que se le creara una cuenta");
                    A = new Account();
                    A.setDatos();
                    
                    dao.makeAccountClient(idMakeCli,A);
                    
                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;

            }
        } while (opc != 8);

    }

    private static int Menu() {

        int opc;

        System.out.println("1.Crear Cliente");
        System.out.println("2.Consultar Datos Cliente");
        System.out.println("3.Consultar Cuentas de un Cliente");
        System.out.println("4.Crear Cuenta para Cliente");
        System.out.println("5.Agregar cliente cuenta");
        System.out.println("6.Consultar Datos de una Cuenta");
        System.out.println("7.Realizar Movimiento Sobre una Cuenta");
        System.out.println("8.Consultar Movimientos de una Cuenta");

        opc = Util.leerInt("Escoge una opcion");
        return opc;
    }

}
