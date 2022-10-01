/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
        Client c = new Client();
        opc = Menu();
        int eleccion;
        do {
            System.out.println("Elige en que quieres guardar la información Ficheros= 1 o BD=2");

            eleccion = Util.leerInt();
            System.out.println("aqiuii"+eleccion);
        } while (eleccion != 1 || eleccion != 2);

        do {

            InterfaceDAO dao;
            if (eleccion == 1) {
                dao = new ImplementFich();
            } else {
                dao = new ImplementBD();
            }

            switch (opc) {

                case 1:
                    System.out.println("A continuación procederemos a solicitar los datos del cliente /n a crear");
                    c.setDatos();
                    dao.createClient(c);
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

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
        } while (true);

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
