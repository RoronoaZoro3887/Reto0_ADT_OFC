/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
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

                    BigDecimal i = BigDecimal.valueOf(Util.leerInt("Introduce el id del "
                            + "cliente a buscar"));
                    c = dao.getDataClient(i);

                    c.getDatos();
                    break;
                case 3:
                    Set<Account> cuentasCli = new HashSet<>();
                    BigDecimal idCuentaCli = BigDecimal.valueOf(Util.leerInt("Introduce el id del usuario"
                            + "a consultar cuentas"));
                    cuentasCli = dao.getAccountClient(idCuentaCli);

                    for (Account Alist : cuentasCli) {
                        Alist.getDatos();
                    }
                    break;
                case 4:
                    Integer idMakeCli = Util.leerInt("Introduce el id del usuario"
                            + "al que se le creara una cuenta");
                    BigDecimal idMcli = BigDecimal.valueOf(idMakeCli);
                    A = new Account();
                    A.setDatos();

                    dao.makeAccountClient(idMcli, A);

                    break;
                case 5:
                    BigDecimal idCuenta = BigDecimal.valueOf(Util.leerInt("Introducir id de la cuenta"
                            + "a la cual añadir un usuario"));
                    BigDecimal idCli = BigDecimal.valueOf(Util.leerInt("Introducir id del cliente"
                            + "al añadir un usuario"));

                    dao.addClientAccount(idCuenta, idCli);
                    break;
                case 6:
                    Account ac = new Account();
                    ac = dao.getDateAccount(BigDecimal.valueOf(Util.leerInt("Introduce el id de la cuenta: ")));
                    ac.getDatos();
                    break;
                case 7:
                    Movement mov = new Movement();
                    mov.setDatos();
                    BigDecimal idCue = BigDecimal.valueOf(Util.leerInt("introduce la cuenta"
                            + "a realizar el movimiento"));
                    System.out.println("introduce la cantidad de dinero");
                    Double money = Util.leerDouble();
                    String desc = mov.getDesc();
                    dao.makeMovements(idCue, money, desc, mov);
                    break;
                case 8:
                    Set<Movement> Movements = new HashSet<>();
                    BigDecimal idCuentaM = BigDecimal.valueOf(Util.leerInt("Introduce el id de"
                            + "la cuenta a consultar los movimientos"));
                    Movements = dao.getMovementAccount(idCuentaM);
                    for (Movement M : Movements) {
                        M.getDatos();
                    }
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
