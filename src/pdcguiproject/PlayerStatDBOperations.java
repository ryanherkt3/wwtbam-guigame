package pdcguiproject;

import java.sql.*;

/**
 * A class which gets the last player's stats from the database and inserts the 
 * most recent player's stats into the database (with the help of 
 * multithreading).
 * 
 * @author Ryan Herkt (Group #: 17, SID: 18022861)
 */
public class PlayerStatDBOperations implements Runnable
{
    private Millionaire_DBManager manager;
    private int[] playerStats;
    
    /**
     * Default constructor. Initializes the DB manager and playerStats as an 
     * integer array of size 2.
     */
    public PlayerStatDBOperations()
    {
        this.manager = new Millionaire_DBManager();
        this.playerStats = new int[2];
    }
    
    /**
     * The multithreading entry point. When this method is called, the second 
     * thread updates the PLAYERSTATS table inside the embedded DB
     */
    @Override
    public void run() 
    {
        updateTable();
    }
    
    /**
     * This method adds in the stats of players who play the game into the 
     * database, updating the database.
     */
    public void updateTable()
    {
        try
        {
            Statement statement = manager.getConn().createStatement();
            String table = "PLAYERSTATS";
            
            String sqlInsert = "insert into " + table + " values(" 
                + getPlayerStats()[0] + ", " + getPlayerStats()[1] + ")";
            statement.executeUpdate(sqlInsert);
            
            System.out.println("Player stats added to " + table);
            statement.close();
        }
        catch (SQLException ex) 
        {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    /**
     * This method gets the stats of the last person who played the game from 
     * the database.
     * 
     * @return an integer array of the last person's stats (questions correct 
     * and money they won)
     */
    public int[] getLastPlayersStats()
    {
        ResultSet rs;
        int[] lastPlayersStats = new int[]{0,0};
        
        try
        {
            Statement statement = manager.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sqlQuery = "select * from PLAYERSTATS";
            
            //printout to console:
            System.out.println("Getting last player's stats!");
            
            rs = statement.executeQuery(sqlQuery);
            
            //get last player's stats:
            rs.afterLast();
            rs.previous();
            lastPlayersStats[0] = rs.getInt("QuestionsCorrect");
            lastPlayersStats[1] = rs.getInt("MoneyWon");

            statement.close();
        }
        catch (SQLException ex) 
        {
            System.err.println("SQLException: " + ex.getMessage());
        }
        
        return lastPlayersStats;
    }
    
    /**
     * @return the playerStats
     */
    public int[] getPlayerStats() {
        return playerStats;
    }

    /**
     * @param playerStats the playerStats to set
     */
    public void setPlayerStats(int[] playerStats) {
        this.playerStats = playerStats;
    }
}