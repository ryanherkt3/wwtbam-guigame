package pdcguiproject;

/**
 * An extracted class (from AskAudience class) which can determine the results 
 * of the AskAudience poll for a particular question. An instance of AskAudience 
 * is created, which reduces the number of primitives created in this class.
 * 
 * This class also reduces the bloating which was previously in the AskAudience 
 * class - both this class and the AskAudience class are now under 100 lines, 
 * if the Javadocs are excluded, whereas in project 1 the AskAudience class 
 * would've been over 200 lines with a massive lifelines method. I believe this 
 * new class also increases the complexity of my program, although this is 
 * only my opinion.
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public class AskAudienceVotes
{
    private int index;
    private AskAudience askAudience;
    
    public AskAudienceVotes(int index, AskAudience askAudience)
    {
        this.askAudience = askAudience;
        this.index = index;
    }
    
     /**
     * Set and return the poll votes based on the difficulty of the question
     * 
     * @param fiftyFifty
     * @param index 
     * @return  
     */
    public int[] setPollVotes(boolean fiftyFifty, int index)
    {
        //weight percentage balance according to question difficulty:
        if (askAudience.getQuestionNumber() < 5)    //an easy question
            return easyVotes(fiftyFifty, index);
        else if (askAudience.getQuestionNumber() < 10)    //a medium question
            return mediumVotes(fiftyFifty, index);
        else //a hard question
            return hardVotes(fiftyFifty, index);
    }
    
    /**
     * Algorithm for setting the poll votes for an easy question. Runs in a 
     * while loop until the sum of all numbers is equal to 100. A higher 
     * weighting is given to the correct answer, even if the 50/50 is also 
     * used.
     * 
     * @param fiftyFifty
     * @param index 
     */
    private int[] easyVotes(boolean fiftyFifty, int index)
    {
        while (askAudience.getVotes()[0] + askAudience.getVotes()[1] + 
                askAudience.getVotes()[2] + askAudience.getVotes()[3] != 100)
        {
            if (!fiftyFifty)
            {
                //min 70 max 100; right answer
                askAudience.getVotes()[0] = askAudience.getInteger().nextInt(31)+70;
                for (int i = 1; i < 4; i++)
                    askAudience.getVotes()[i] = askAudience.getInteger().nextInt(11)
                            +askAudience.getInteger().nextInt(6);//0-15
            }
            else
            {
                //min 75 max 100, right answer
                askAudience.getVotes()[index] = askAudience.getInteger().nextInt(31)+75;
                askAudience.getVotes()[askAudience.option] = 
                        askAudience.getInteger().nextInt(26);     //0-25
            }
        }
        return askAudience.getVotes();
    }
    
    /**
     * Algorithm for setting the poll votes for a medium question. Runs in a 
     * while loop until the sum of all numbers is equal to 100. A higher 
     * weighting is given to the correct answer, even if the 50/50 is also 
     * used.
     * 
     * @param fiftyFifty
     * @param index 
     */
    private int[] mediumVotes(boolean fiftyFifty, int index)
    {
        while (askAudience.getVotes()[0] + askAudience.getVotes()[1] + 
                askAudience.getVotes()[2] + askAudience.getVotes()[3] != 100)
        {
            if (!fiftyFifty)
            {
                //min 45 max 70; right answer
                askAudience.getVotes()[0] = askAudience.getInteger().nextInt(26)+45;
                for (int i = 1; i < 4; i++)
                    askAudience.getVotes()[i] = askAudience.getInteger().nextInt(11)
                            +askAudience.getInteger().nextInt(16)+10;//10-25
            }
            else
            {
                //min 50 max 90, right answer
                askAudience.getVotes()[index] = askAudience.getInteger().nextInt(46)+50;
                askAudience.getVotes()[askAudience.option] = 
                        askAudience.getInteger().nextInt(41)+10;    //10-50
            }
        }
        return askAudience.getVotes();
    }
    
    /**
     * Algorithm for setting the poll votes for an easy question. Runs in a 
     * while loop until the sum of all numbers is equal to 100. A higher 
     * weighting is given to the correct answer, but not if the 50/50 is also 
     * used.
     * 
     * @param fiftyFifty
     * @param index 
     */
    private int[] hardVotes(boolean fiftyFifty, int index)
    {
        while (askAudience.getVotes()[0] + askAudience.getVotes()[1] + 
                askAudience.getVotes()[2] + askAudience.getVotes()[3] != 100)
        {
            if (!fiftyFifty)
            {
                //min 20 max 55; right answer
                askAudience.getVotes()[0] = askAudience.getInteger().nextInt(36)+20;
                for (int i = 1; i < 4; i++)
                    askAudience.getVotes()[i] = askAudience.getInteger().nextInt(11)+
                            askAudience.getInteger().nextInt(26)+15;    //15-40
            }    
            else
            {
                //min 35 max 100 (both):
                askAudience.getVotes()[index] = askAudience.getInteger().nextInt(66)+35;
                askAudience.getVotes()[askAudience.option] = 
                        askAudience.getInteger().nextInt(66)+35;
            }
        }
        return askAudience.getVotes();
    }
}