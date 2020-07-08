package pdcguiproject;

import java.sql.*;

/**
 * A class which gets questions from the database. There was another operation 
 * performed on the database from this class - adding questions to the database.
 * Because this has now been done, that code is redundant. But you can still see 
 * the steps and process I used in the text file 'How to add Questions to 
 * Database' if you want more info.
 * 
 * @author Ryan Herkt (Group #: 17, SID: 18022861)
 */
public class QuestionDBOperations
{
    private final Millionaire_DBManager manager;
    private Question question;
    private int ID;
    
    public QuestionDBOperations() 
    {
        this.manager = new Millionaire_DBManager();
        this.question = new Question("", "", "", "", "", "");
        this.ID = 0;
    }
    
    /**
     * This class gets all the values from a specified row in the questions 
     * column (using the ID number), adds them into a question instance, 
     * and sets the question as the question instance.
     */
    public void setQuestion() 
    {
        ResultSet rs;        
        try 
        {
            Statement statement = manager.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String sqlQuery = "select * from questions where ID = " + getID();
            
            //printout to console:
            System.out.println("Getting question #" + getID());
            
            rs = statement.executeQuery(sqlQuery);
            rs.beforeFirst();
            
            Question q = new Question("", "", "", "", "", "");
            while (rs.next()) 
            {
                //Capture all relevant values:
                String toAsk = rs.getString("question");
                String ansA = rs.getString("ansA");
                String ansB = rs.getString("ansB");
                String ansC = rs.getString("ansC");
                String ansD = rs.getString("ansD");
                String right = rs.getString("correctans");
                
                //Update Question variable q with newly captured values:
                q.setQuestion(toAsk);
                q.setAnswers(new String[]{ansA, ansB, ansC, ansD});
                q.setRightAns(right);
                this.question = q;
            }
            
            //Close up:
            statement.close();
            rs.close();
        } 
        catch (SQLException ex){}
    }

    /**
     * @return the question ID
     */
    public int getID() 
    {
        return ID;
    }
    
    /**
     * @return the question ID
     */
    public Question getQuestion() 
    {
        return question;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) 
    {
        this.ID = ID;
    }
}