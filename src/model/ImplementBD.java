/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 2dam
 */
public class ImplementBD implements InterfaceDAO{
    
    
    
    
    
    
    
    
    
    
    
    
    
    private final String createClient = "INSERT INTO customer(city, email, firstName, lastName, middleInitial, phone, state, street, zip) VALUES(?,?,?,?,?,?,?,?,?)";
    private final String getDataClient = "SELECT * FROM customer WHERE id = ?";
    private final String getAccountClient ="SELECT * FROM Account WHERE id = (SELECT accounts_id FORM customer_account WHERE customer_id = ?)";
    private final String makeAccountClient ="INSERT INTO Account(balance, beginBalance, beginBalanceTimestamp, creditLine, description, type) VALUES(?,?,?,?,?,?)";
    private final String addClientAccount = "INSERT INTO customer_account(customers_id, accounts_id) VALUES(?,?)";  
    private final String getDateAccount ="SELECT * FROM account WHERE id = ?";
    private final String makeMovements = "INSERT INTO movement(amount, balance, description, account_id) VALUES(?,?,?, (SELECT id FROM account WHERE id = ?))";
    private final String makeMovementsRest = "UPDATE movement SET balance = balance - amount WHERE id = (SELECT id FROM account WHERE id = ?)";
    private final String makeMovementsPlus = "UPDATE movement SET balance = balance + amount WHERE id = (SELECT id FROM account WHERE id = ?)";
    private final String getMovementAccount = "SELECT * FROM movement WHERE account_id = (SELECT id FROM account WHERE id = ?)";
    
    @Override
    public void createClient(Client cli) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
