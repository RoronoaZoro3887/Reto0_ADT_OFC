/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilidades.MiObjectOutputStream;

/**
 *
 * @author 2dam
 */
public class ImplementFich implements InterfaceDAO {

    private File client = new File("client.obj");

    private static void dumpArrayFich(List<Client> clientList, File recursos) throws IOException {
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(recursos));
            for (Client clie : clientList) {
                oos.writeObject(clie);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                oos.flush();
                oos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    private static List<Client> dumpFichArray(File client) {
        List<Client> clientList = new ArrayList<>();
        ObjectInputStream ois = null;
        Client clie;

        try {
            ois = new ObjectInputStream(new FileInputStream(client));
            do {
                clie = (Client) ois.readObject();
                clientList.add(clie);
            } while (true);
        } catch (EOFException e) {
            System.out.println("Fin de fichero");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return clientList;
    }

    @Override
    public void createClient(Client cli) {
        ObjectOutputStream oos = null;
        MiObjectOutputStream moos = null;
        List<Client> clientList;
        Client clie;

        if (client.exists()) {
            clientList = dumpFichArray(client);

            try {
                moos = new MiObjectOutputStream(new FileOutputStream(client, true));
                moos.writeObject(cli);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ImplementFich.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ImplementFich.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    moos.flush();
                    moos.close();
                } catch (IOException ex) {
                    Logger.getLogger(ImplementFich.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                oos = new ObjectOutputStream(new FileOutputStream(client));
                oos.writeObject(cli);

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    oos.flush();
                    oos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public Client getDataClient(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Account getAccountClient(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void makeAccountClient(Integer id, Account ac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addClientAccount(Integer idcuen, Integer idcli) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Account getDateAccount(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void makeMovements(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Movement getMovementAccount(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
