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
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilidades.MiObjectOutputStream;

/**
 *
 * @author 2dam
 */
public class ImplementFich implements InterfaceDAO, Serializable {

    private File client = new File("client.obj");

    private static void dumpArrayFich(Set<Client> clientList, File recursos) {
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

    private static Set<Client> dumpFichArray(File client) {
        Set<Client> clientList = new HashSet<>();
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
        Client clie;

        if (client.exists()) {
            System.out.println(cli);
            cli.getDatos();

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
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else {
            try {
                oos = new ObjectOutputStream(new FileOutputStream(client));
                oos.writeObject(cli);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ImplementFich.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ImplementFich.class.getName()).log(Level.SEVERE, null, ex);
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
    public Client getDataClient(BigDecimal id) {
        Client c = null;
        Set<Client> clientArray = dumpFichArray(client);
        for (Client cli : clientArray) {
            if (cli.getId().equals(id)) {
                c = cli;
            }
        }
        return c;
    }

    public Set<Account> getAccountClient(BigDecimal id) {
        Set<Client> clientArray = dumpFichArray(client);
        Set<Account> set = new HashSet<>();
        for (Client cli : clientArray) {
            if (cli.getId().equals(id)) {
                set = cli.getAccountList();
            }
        }
        return set;
    }

    @Override
    public void makeAccountClient(BigDecimal id, Account ac) {
        MiObjectOutputStream moos = null;
        Set<Client> clientArray = dumpFichArray(client);
        List<Client> clientList = devolverArrayList(clientArray);

        for (int i = 0; i < clientList.size(); i++) {
            if (clientList.get(i).getId().equals(id)) {
                clientList.get(i).addAccount(ac);
            }
        }
        clientArray = devolverSet(clientList);
        dumpArrayFich(clientArray, client);

    }

    @Override
    public void addClientAccount(BigDecimal idcuen, BigDecimal idcli) {
        Account account = new Account();
        MiObjectOutputStream moos = null;
        Set<Client> clientArray = dumpFichArray(client);
        List<Client> clientList = devolverArrayList(clientArray);
        account = getDateAccount(idcuen);

        for (int i = 0; i < clientList.size(); i++) {
            if (clientList.get(i).getId().equals(idcli)) {
                clientList.get(i).addAccount(account);
            }

        }

        clientArray = devolverSet(clientList);
        dumpArrayFich(clientArray, client);
    }

    @Override
    public Account getDateAccount(BigDecimal id) {
        Account account = new Account();
        Set<Client> clientArray = dumpFichArray(client);

        for (Client cli : clientArray) {
            for (Account ac : cli.getAccountList()) {
                if (ac.getId().equals(id)) {
                    account = ac;
                }

            }
        }
        return account;
    }

    @Override
    public void makeMovements(BigDecimal id, Double amount, String desc, Movement M) {

        Set<Client> clientArray = dumpFichArray(client);
        List<Client> cliArray = devolverArrayList(clientArray);
        for (Client cli : cliArray) {
            for (Account ac : cli.getAccountList()) {
                if (ac.getId().equals(id)) {
                    if (desc.equals("payment")) {
                        Double balance = ac.getBalance();
                        balance -= amount;
                        ac.setBalance(balance);
                        ac.addMovement(M);
                    } else if (desc.equals("deposit")) {
                        Double balance = ac.getBalance();
                        balance += amount;
                        ac.setBalance(balance);
                        ac.addMovement(M);
                    }

                }

            }
        }

        clientArray = devolverSet(cliArray);
        dumpArrayFich(clientArray, client);

    }

    @Override
    public Set<Movement> getMovementAccount(BigDecimal id
    ) {
        Set<Client> clientArray = dumpFichArray(client);
        Set<Movement> set = new HashSet<>();

        for (Client cli : clientArray) {
            for (Account ac : cli.getAccountList()) {
                if (ac.getId().equals(id)) {
                    set = ac.getMovementList();
                }
            }
        }
        return set;
    }

    private List<Client> devolverArrayList(Set<Client> clientArray) {
        List<Client> clientList = new ArrayList<>();

        for (Client cli : clientArray) {
            clientList.add(cli);
        }
        return clientList;
    }

    private Set<Client> devolverSet(List<Client> clientList) {
        Set<Client> clientArray = new HashSet<>();

        for (Client cli : clientList) {
            clientArray.add(cli);
        }
        return clientArray;
    }

}
