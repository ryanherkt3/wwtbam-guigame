package pdcguiproject;

import java.sql.*;

/**
 * This class establishes the connection to the database and also gets it 
 * when asked by the two classes PlayerStatDBOperations and 
 * QuestionDBOperations, which both use multithreading to get the connection. 
 * This class implements the Runnable interface
 * 
 * @author Weihua "Leo" Li (original)
 * @author Ryan Herkt (adapted, Group #: 17, SID: 18022861)
 */
public final class Millionaire_DBManager
{
    //Database connections:
    private final String USER_NAME = "millionaire";
    private final String PASSWORD = "millionaire";
    private final String URL = "jdbc:derby:MillionaireGameDB;create=true";
    Connection conn;
    
    public Millionaire_DBManager()
    {
        establishConnection();
    }

    /**
     * Establishes the connection
     */
    public void establishConnection() 
    {
        if (this.getConn() == null) 
        {
            try 
            {
                setConn(DriverManager.getConnection(URL, USER_NAME, PASSWORD));
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex.getMessage());
            }
        }
    }    
    
    /**
     * Closes the connection
     */
    public void closeConnections() 
    {
        if (getConn() != null) 
        {
            try 
            {
                getConn().close();
            } 
            catch (SQLException ex) {}
        }
    }

    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * @param conn the conn to set
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }
}