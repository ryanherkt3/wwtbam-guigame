package pdcguiproject;

import java.sql.*;

/**
 * A class which gets inserts the name of the lifeline the current player used, 
 * and the question number it was used on into the database (with the help of 
 * multi-threading).
 * 
 * @author Ryan Herkt (Group #: 17, SID: 18022861)
 */
public class LifelineDBOperations implements Runnable
{
    private Millionaire_DBManager manager;
    private String lifeline;
    private int questionNumber;
    
    /**
     * Default constructor. Initializes the DB manager, the lifeline String as 
     * empty, and the questionNumber int as 0.
     */
    public LifelineDBOperations()
    {
        this.manager = new Millionaire_DBManager();
        this.lifeline = "";
        this.questionNumber = 0;
    }
    
    /**
     * The multithreading entry point. When this method is called, the second 
     * thread updates the LIFELINES table inside the embedded DB
     */
    @Override
    public void run() 
    {
        updateTable();
    }
    
    /**
     * This method adds in the name of the lifeline the current player used, 
     * and the question number it was used on into the database.
     * 
     * How I created this table can be found in the text file 'How I Created 
     * My Database Tables'
     */
    public void updateTable()
    {
        try
        {
            Statement statement = manager.getConn().createStatement();
            String table = "LIFELINES";
            
            String sqlInsert = "insert into " + table + " values('" 
                + getLifeline() + "', " + getQuestionNumber() + ")";
            statement.executeUpdate(sqlInsert);
        }
        catch(SQLException ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    /**
     * @return the lifeline
     */
    public String getLifeline() {
        return lifeline;
    }

    /**
     * @param lifeline the lifeline to set
     */
    public void setLifeline(String lifeline) {
        this.lifeline = lifeline;
    }

    /**
     * @return the questionNumber
     */
    public int getQuestionNumber() {
        return questionNumber;
    }

    /**
     * @param questionNumber the questionNumber to set
     */
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }
}