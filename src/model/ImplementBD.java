/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2dam
 */
public class ImplementBD implements InterfaceDAO {

    /**
     * Consulas BD
     */
    private final String createClient = "";
    private final String getDataClient = "";
    private final String getAccountClient = "";
    private final String makeAccountClient = "";
    private final String addClientAccount = "";
    private final String getDateAccount = "";
    private final String makeMovements = "";
    private final String getMovementAccount = "";

       
    /**
     * Conexión BD
     */
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
            stmt = (PreparedStatement) con.prepareStatement(createClient);
            
            stmt.setString(1, cli.getCity());
            stmt.setString(2, cli.getEmail());
            stmt.setString(3, cli.getFirstName());
            stmt.setString(4, cli.getLastName());
            stmt.setString(5, cli.getMiddleIntial());
            stmt.setInt(6, cli.getPhone());
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
    public Client getDataClient(Integer id) {
        ResultSet rs= null;
        ResultSet rs2= null;
        Client cli = null;
        Account ac=null;
        this.openConnection();
        
        try {
            stmt= (PreparedStatement) con.prepareStatement(getDataClient);
            stmt.setInt(1, id);
            
            rs= stmt.executeQuery();
            
            Set<Account> accountList = null;
            while (rs.next()) {
                cli = new Client();

                cli.setId(id);
                cli.setCity(rs.getString("city"));
                cli.setEmail(rs.getString("email"));
                cli.setFirstName(rs.getString("firstName"));
                cli.setLastName(rs.getString("lastName"));
                cli.setMiddleIntial(rs.getString("middleInitial"));
                cli.setPhone(rs.getInt("phone"));
                cli.setState(rs.getString("state"));
                cli.setStreet(rs.getString("street"));
                cli.setZip(rs.getInt("state"));
                PreparedStatement stmt2 = (PreparedStatement) 
                        con.prepareStatement(getAccountClient);
                stmt2.setInt(1, id);
                rs2= stmt2.executeQuery();
                 
                while (rs2.next()) {  
                    accountList= new HashSet<>();
                    ac.setId(rs2.getInt("id"));
                    ac.setBalance(rs2.getDouble("balance"));
                    ac.setBeginBalance(rs2.getDouble("beginBalance"));
                    ac.setbBTs(rs2.getDate("beginBalanceTimestamp")
                            .toLocalDate());
                    ac.setCreditLine(rs2.getDouble("creditLine"));
                    ac.setDesc(rs2.getString("description"));

                    if (rs2.getInt("type") == 1) {
                        ac.setType(AccountType.STANDAR);
                    } else {
                        ac.setType(AccountType.CREDIT);
                    }                       
                }
                accountList.add(ac);
            }
            cli.setAccountList(accountList);
           
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
    public Account getAccountClient(Integer id) {
        ResultSet rs= null;
        Account ac = null;
        Set<Account> accountList = null;
        this.openConnection();
        
        try {
         PreparedStatement stmt = (PreparedStatement) 
                        con.prepareStatement(getAccountClient);
                stmt.setInt(1, id);
                rs= stmt.executeQuery();
                 
                while (rs.next()) {  
                    accountList= new HashSet<>();
                    ac.setId(rs.getInt("id"));
                    ac.setBalance(rs.getDouble("balance"));
                    ac.setBeginBalance(rs.getDouble("beginBalance"));
                    ac.setbBTs(rs.getDate("beginBalanceTimestamp")
                            .toLocalDate());
                    ac.setCreditLine(rs.getDouble("creditLine"));
                    ac.setDesc(rs.getString("description"));

                    if (rs.getInt("type") == 1) {
                        ac.setType(AccountType.STANDAR);
                    } else {
                        ac.setType(AccountType.CREDIT);
                    }                       
                }
                accountList.add(ac);
           
           
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
        return ac;
    }

    @Override
    public void makeAccountClient(Integer id, Account ac) {
         
       this.openConnection();
      
        
        try {
            stmt = (PreparedStatement) con.prepareStatement(makeAccountClient);
            
            stmt.setString(1, ac.getCity());
            stmt.setString(2, ac.getEmail());
            stmt.setString(3, ac.getFirstName());
            stmt.setString(4, ac.getLastName());
            stmt.setString(5, ac.getMiddleIntial());
            stmt.setInt(6, ac.getPhone());
            stmt.setString(7, ac.getState());
            stmt.setString(8, ac.getStreet());
            stmt.setInt(9, ac.getZip());
       
            stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ImplementBD.class.getName()).log(Level.SEVERE,
                    null, ex);
        } finally{
            this.closeConnection();
        }
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
