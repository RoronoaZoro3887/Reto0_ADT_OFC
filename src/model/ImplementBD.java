/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2dam
 */
public class ImplementBD implements InterfaceDAO{
 private final String createClient = "INSERT INTO customer(city, email, "
            + "firstName, lastName, middleInitial, phone, state, street, zip) "
            + "VALUES(?,?,?,?,?,?,?,?,?)";
    private final String getDataClient = "SELECT * FROM customer WHERE id = ?";
    private final String getAccountClient ="SELECT * FROM Account WHERE id in "
            + "(SELECT accounts_id FROM customer_account "
            + "WHERE customers_id = ?)";
    private final String makeAccountClient ="INSERT INTO Account(balance, "
            + "beginBalance, beginBalanceTimestamp, creditLine, "
            + "description, type) VALUES(?,?,CURRENT_TIMESTAMP(),?,?,?)";
    private final String addClientAccount = "INSERT INTO "
            + "customer_account(customers_id, accounts_id) VALUES(?,?)";  
    private final String getDateAccount ="SELECT * FROM account WHERE id = ?";
    private final String makeMovements = "INSERT INTO movement(amount, "
            + "balance, description, timestamp, account_id) VALUES(?,(select "
            + "balance from account where id = ?),?,CURRENT_TIMESTAMP(),(SELECT id FROM "
            + "account WHERE id = ?))";
    private final String comprobaridCuenta = "select id from account where id = ?";
    private final String comprobaridCliente = "select id from customer where id = ?";
    private final String updateAccountBalance ="Update account SET balance =\n"+
            "(SELECT balance from movement where\n" +
            "timestamp= (select max(timestamp)\n" +
            " from movement where account_id= ?))\n" +
            " where id= ? ";
    private final String makeMovementsEquation = "UPDATE movement SET balance = "
            + "balance + amount WHERE timestamp = ?";
    private final String getMovementAccount = "SELECT * FROM movement "
            + "WHERE account_id in (SELECT id FROM account WHERE id = ?)";
    private final String conectarCuentaCreada = "INSERT INTO "
            + "customer_account(customers_id, accounts_id) VALUES(?,(select id "
            + "from account where beginBalanceTimestamp =\n" +
            "(select max(beginBalanceTimestamp) from account)))";
    private final String conseguirTimeStamp ="select max(timestamp) "
            + "from movement where account_id = ?";

       
  
    private String driverBD;
    private String urlBD;
    private String userBD;
    private String passwordBD;
    
    private Connection con;
    private PreparedStatement stmt;

    public ImplementBD(){
     this.driverBD= ResourceBundle.getBundle("controller.config")
                .getString("driver");
        this.urlBD= ResourceBundle.getBundle("controller.config")
                .getString("urlBD");
        this.userBD= ResourceBundle.getBundle("controller.config")
                .getString("userBD");
        this.passwordBD= ResourceBundle.getBundle("controller.config")
                .getString("passwordBD");
    }
    
    public void openConnection (){
        try {
            con= (Connection) DriverManager.getConnection
        (urlBD, userBD, passwordBD);
        } catch (SQLException ex) {
            Logger.getLogger(ImplementBD.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnection() {
        if (stmt!= null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImplementBD.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
        if (con!= null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImplementBD.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
    @Override
    public void createClient(Client cli) {
      
        this.openConnection();
        
        try {
            stmt =con.prepareStatement(createClient);
            
            stmt.setString(1, cli.getCity());
            stmt.setString(2, cli.getEmail());
            stmt.setString(3, cli.getFirstName());
            stmt.setString(4, cli.getLastName());
            stmt.setString(5, cli.getMiddleIntial());
            stmt.setBigDecimal(6, cli.getPhone());
            stmt.setString(7, cli.getState());
            stmt.setString(8, cli.getStreet());
            stmt.setInt(9, cli.getZip());
            
            stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ImplementBD.class.getName()).log(Level.SEVERE,
                    null, ex);
        } finally{
            this.closeConnection();
        }
    }

    @Override
    public Client getDataClient(BigDecimal id) {
        Movement mv = null;
        ResultSet rs= null;
        ResultSet rs2= null;
        ResultSet rs3= null;
        Client cli = null;
        Account ac=null;
        Set<Movement> movementList = null;
        Set<Account> accountList = new HashSet<>();
        
        this.openConnection();
        
        try {
            stmt= con.prepareStatement(getDataClient);
            stmt.setBigDecimal(1, id);
            
            rs= stmt.executeQuery();
            
            
            while (rs.next()) {
                cli = new Client();

                cli.setId(id);
                cli.setCity(rs.getString("city"));
                cli.setEmail(rs.getString("email"));
                cli.setFirstName(rs.getString("firstName"));
                cli.setLastName(rs.getString("lastName"));
                cli.setMiddleIntial(rs.getString("middleInitial"));
                cli.setPhone(rs.getBigDecimal("phone"));
                cli.setState(rs.getString("state"));
                cli.setStreet(rs.getString("street"));
                cli.setZip(rs.getInt("zip"));
                
                PreparedStatement stmt2 = (PreparedStatement) 
                        con.prepareStatement(getAccountClient);
                stmt2.setBigDecimal(1, id);
                rs2= stmt2.executeQuery();
                 
                while (rs2.next()) {  
                    ac = new Account();
                    movementList = new HashSet<>();
                    ac.setId(rs2.getBigDecimal("id"));
                    ac.setBalance(rs2.getDouble("balance"));
                    ac.setBeginBalance(rs2.getDouble("beginBalance"));
                    ac.setbBTs(rs2.getTimestamp("beginBalanceTimestamp")
                            );
                    ac.setCreditLine(rs2.getDouble("creditLine"));
                    ac.setDesc(rs2.getString("description"));

                    if (rs2.getInt("type") == 1) {
                        ac.setType(AccountType.STANDAR);
                    } else {
                        ac.setType(AccountType.CREDIT);
                    }                       
                    accountList.add(ac);
                
                cli.setAccountList(accountList);
                
                PreparedStatement stmt3 =
                        con.prepareStatement(getMovementAccount);
            stmt3.setBigDecimal(1, rs2.getBigDecimal("id"));
            rs3 = stmt3.executeQuery();

            while (rs3.next()) {
                mv = new Movement();
                mv.setId(rs3.getInt("id"));
                mv.setAmount(rs3.getDouble("amount"));
                mv.setBalance(rs3.getDouble("balance"));
                mv.setDesc(rs3.getString("description"));
                mv.setbBTs(rs3.getTimestamp("timestamp"));
                mv.setAccountId(rs3.getBigDecimal("account_id"));
                movementList.add(mv);
            }  
            
               ac.setMovementList(movementList);
                }
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ImplementBD.class.getName())
                            .log(Level.SEVERE, null, ex);
        } finally{
            if (rs!= null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ImplementBD.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
                this.closeConnection();
        }
        return cli;
    }

    @Override
    public Set<Account> getAccountClient(BigDecimal id) {
        ResultSet rs= null;
        ResultSet rs2= null;
        Movement mv = null;
        Set<Movement> movementList = new HashSet<>();
        Account ac = null;
        Set<Account> accountList = new HashSet<>();
        this.openConnection();
        
        try {
                stmt = con.prepareStatement(getAccountClient);
                stmt.setBigDecimal(1, id);
                rs= stmt.executeQuery();
                 
                while (rs.next()) {  
                    ac = new Account();
                    movementList = new HashSet<>();
                    ac.setId(rs.getBigDecimal("id"));
                    ac.setBalance(rs.getDouble("balance"));
                    ac.setBeginBalance(rs.getDouble("beginBalance"));
                    ac.setbBTs(rs.getTimestamp("beginBalanceTimestamp")
                            );
                    ac.setCreditLine(rs.getDouble("creditLine"));
                    ac.setDesc(rs.getString("description"));

                    if (rs.getInt("type") == 1) {
                        ac.setType(AccountType.STANDAR);
                    } else {
                        ac.setType(AccountType.CREDIT);
                    }                       
                    
                
                PreparedStatement stmt2 =
                        con.prepareStatement(getMovementAccount);
            stmt2.setBigDecimal(1, rs.getBigDecimal("id"));
            rs2 = stmt2.executeQuery();

            while (rs2.next()) {
                mv = new Movement();
                mv.setId(rs2.getInt("id"));
                mv.setAmount(rs2.getDouble("amount"));
                mv.setBalance(rs2.getDouble("balance"));
                mv.setDesc(rs2.getString("description"));
                mv.setbBTs(rs2.getTimestamp("timestamp"));
                mv.setAccountId(rs2.getBigDecimal("account_id"));
                movementList.add(mv);
            }  
            
               ac.setMovementList(movementList);
               accountList.add(ac);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImplementBD.class.getName())
                            .log(Level.SEVERE, null, ex);
        } finally{
            if (rs!= null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ImplementBD.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
                this.closeConnection();
        }
        return accountList;
    }

    @Override
    public void makeAccountClient(BigDecimal id, Account ac) {
         
       this.openConnection();
      
        
        try {
            stmt = con.prepareStatement(makeAccountClient);
            
            stmt.setDouble(1, ac.getBalance());
            stmt.setDouble(2, ac.getBeginBalance());
            stmt.setDouble(3, ac.getCreditLine());
            stmt.setString(4, ac.getDesc());
             if ("STANDAR".equals(ac.getType().toString())) {
                        stmt.setInt(5, 1);
                    } else {
                       stmt.setInt(5, 0);
                    }            
            stmt.executeUpdate();
            
            PreparedStatement stmt2 =
                        con.prepareStatement(conectarCuentaCreada);
            
            stmt2.setBigDecimal(1, id);
            stmt2.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ImplementBD.class.getName()).log(Level.SEVERE,
                    null, ex);
        } finally{
            this.closeConnection();
        }
    }

    @Override
    public void addClientAccount(BigDecimal idcuen, BigDecimal idcli) {
        
        this.openConnection();
      
        
        try {
          
            stmt = con.prepareStatement(addClientAccount);
            
            stmt.setBigDecimal(1, idcli);
            stmt.setBigDecimal(2, idcuen);
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ImplementBD.class.getName()).log(Level.SEVERE,
                    null, ex);
        } finally{
            this.closeConnection();
        }
    }

    @Override
    public Account getDateAccount(BigDecimal id) {
        ResultSet rs= null;
        ResultSet rs2= null;
        Account ac = null;
        Movement mv= null;
        Set<Movement> movementList = new HashSet<>();
        this.openConnection();
        
        try {
                stmt = con.prepareStatement(getDateAccount);
                stmt.setBigDecimal(1, id);
                rs= stmt.executeQuery();
                 
                while (rs.next()) {  
                    ac = new Account();
                    ac.setId(rs.getBigDecimal("id"));
                    ac.setBalance(rs.getDouble("balance"));
                    ac.setBeginBalance(rs.getDouble("beginBalance"));
                    ac.setbBTs(rs.getTimestamp("beginBalanceTimestamp")
                            );
                    ac.setCreditLine(rs.getDouble("creditLine"));
                    ac.setDesc(rs.getString("description"));

                    if (rs.getInt("type") == 1) {
                        ac.setType(AccountType.STANDAR);
                    } else {
                        ac.setType(AccountType.CREDIT);
                    }                       
                }
                PreparedStatement stmt2 =
                        con.prepareStatement(getMovementAccount);
            stmt2.setBigDecimal(1, id);
            rs2 = stmt2.executeQuery();

            while (rs2.next()) {
                mv = new Movement();
                mv.setId(rs2.getInt("id"));
                mv.setAmount(rs2.getDouble("amount"));
                mv.setBalance(rs2.getDouble("balance"));
                mv.setDesc(rs2.getString("description"));
                mv.setbBTs(rs2.getTimestamp("timestamp"));
                mv.setAccountId(rs2.getBigDecimal("account_id"));
                if (mv != null) {
                    movementList.add(mv);

                }
            
            }
            ac.setMovementList(movementList); 
        } catch (SQLException ex) {
            Logger.getLogger(ImplementBD.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ImplementBD.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            this.closeConnection();
        }
        return ac;
    }

    @Override
    public void makeMovements(BigDecimal id, Double amount, String desc, Movement mv) {
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        PreparedStatement stmt4 = null;
        ResultSet rs = null;
        Timestamp stamp = null;
        this.openConnection();
        try {
            stmt = con.prepareStatement(makeMovements);
            
            stmt.setDouble(1, amount);
            stmt.setBigDecimal(2, id);
            stmt.setString(3, desc);
            stmt.setBigDecimal(4, id);
            
            stmt.executeUpdate();
            
            stmt4 = con.prepareStatement(conseguirTimeStamp);
            rs = stmt4.executeQuery();
            if(rs.next()){
                stamp = rs.getTimestamp("timestamp");
            }
            
            stmt2 = con.prepareStatement(makeMovementsEquation);
            stmt2.setTimestamp(1, stamp);
            stmt2.executeUpdate();
            
            
            
            stmt3 = con.prepareStatement(updateAccountBalance);
            stmt3.setBigDecimal(1, id);
            stmt3.setBigDecimal(2, id);
            stmt3.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ImplementBD.class.getName()).
                    log(Level.SEVERE, null, ex);
        }finally{
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ImplementBD.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            this.closeConnection();
        }
        
    }

    @Override
    public Set<Movement> getMovementAccount(BigDecimal id) {
        ResultSet rs = null;
        Movement mv = null;
        Set<Movement> movementList = new HashSet<>();
        this.openConnection();

        try {
            stmt = con.prepareStatement(getMovementAccount);
            stmt.setBigDecimal(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                mv = new Movement();
                mv.setId(rs.getInt("id"));
                mv.setAmount(rs.getDouble("amount"));
                mv.setBalance(rs.getDouble("balance"));
                mv.setDesc(rs.getString("description"));
                mv.setbBTs(rs.getTimestamp("timestamp"));
                mv.setAccountId(id);
                movementList.add(mv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImplementBD.class.getName())
                    .log(Level.SEVERE, null, ex);
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ImplementBD.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            this.closeConnection();
        }
        return movementList;
    }
    
    @Override
    public boolean comprobarCuenta(BigDecimal idCuenta){
        ResultSet rs = null;
        BigDecimal id = null;
        this.openConnection();

        try {
            stmt = con.prepareStatement(comprobaridCuenta);
            stmt.setBigDecimal(1, idCuenta);
            rs = stmt.executeQuery();
            
            if(rs.next()){
               id = rs.getBigDecimal("id");
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ImplementBD.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ImplementBD.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            this.closeConnection();
        }
        if(id == null){
                return false;
            }else{
                return true;
            }
    }
    
    @Override
    public boolean comprobarCliente(BigDecimal idCliente){
        
        ResultSet rs = null;
        BigDecimal id = null;
        this.openConnection();

        try {
            stmt = con.prepareStatement(comprobaridCliente);
            stmt.setBigDecimal(1, idCliente);
            rs = stmt.executeQuery();
            
            if(rs.next()){
               id = rs.getBigDecimal("id");
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ImplementBD.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ImplementBD.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            this.closeConnection();
        }
        if(id == null){
                return false;
            }else{
                return true;
            }
    }

}
