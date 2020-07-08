package pdcguiproject;

/**
 * A class which performs multithreaded operations within my project as an 
 * advanced feature. The methods contain more detail. There is one variable - 
 * a questionsCorrect integer, taken from the main game method; it is 
 * encapsulated.
 * 
 * The two methods which perform the multithreaded operations were originally 
 * in the main class, but I brought them into their own class to reduce the 
 * bloating.
 * 
 * @author Ryan Herkt (Group #: 17, SID: 18022861)
 */
public class MultiThreadOperations 
{
    private int questionsCorrect;
    
    public MultiThreadOperations(int questionsCorrect)
    {
        this.questionsCorrect = questionsCorrect;
    }
    
    /**
     * A method which uses multi-threading to update the Lifelines table in my 
     * embedded database. 
     * 
     * A new LifelineDBOperations instance is created, then the string lifeline 
     * and int question number values are set via encapsulation based on 
     * existing values within the game. Then, the thread starts
     * 
     * @param lifeline the lifeline used as String
     */
    public void recordLifelineUsage(String lifeline)
    {
        LifelineDBOperations lifelineOps = new LifelineDBOperations();
        lifelineOps.setLifeline(lifeline);
        lifelineOps.setQuestionNumber((getQuestionsCorrect()+1));
        
        Thread t = new Thread(lifelineOps);
        t.start();
    }
    
    /**
     * A method which uses multi-threading to update the PlayerStats table in my 
     * embedded database. 
     * 
     * The existing PlayerStatDBOperations instance is used, then the 
     * player stats array is set via encapsulation based on the existing values 
     * within the game. Then, the thread starts.
     * 
     * @param money the won by user
     */
    public void recordPlayerStats(int money)
    {
        PlayerStatDBOperations playerStats = new PlayerStatDBOperations();
        playerStats.setPlayerStats(new int[]{getQuestionsCorrect(), money});
        
        Thread t = new Thread(playerStats);
        t.start();
    }

    /**
     * @return the questionsCorrect
     */
    public int getQuestionsCorrect() 
    {
        return questionsCorrect;
    }

    /**
     * @param questionsCorrect the questionsCorrect to set
     */
    public void setQuestionsCorrect(int questionsCorrect) 
    {
        this.questionsCorrect = questionsCorrect;
    }
}
