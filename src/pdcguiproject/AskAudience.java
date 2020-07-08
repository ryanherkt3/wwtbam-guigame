package pdcguiproject;

/**
 * This AskAudience class contains the algorithm for the Ask the Audience lifeline 
 * (lifeline method). It also has a default constructor and boolean to check 
 * if the 50/50 lifeline was used. This boolean has encapsulation methods.
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public class AskAudience extends Lifelines
{
    private boolean FFused;
    
    /**
     * This default AskAudience constructor only calls the Lifelines superclass, 
     * as all variables are declared there in its constructor.
     */
    public AskAudience() 
    {
        super();
    }
    
    /**
     * This method contains the algorithm for the Ask the Audience lifeline. 
     * The algorithm has two cases (1: 50/50 used prior to the question, 2: 50/50
     * not used before this lifeline on the same question). 
     * 
     * The HashMap answerVotes has four keys put in it (each of the four 
     * possible answers from the allAnswers string array), with each value set 
     * to zero, and the index number of the correct answer is recorded 
     * (for case 2 if it happens).
     * 
     * A do-while loop sets the option variable, until 
     * question.getAnswers()[option] is the third wrong answer.
     * 
     * An instance of AskAudienceVotes is created, passing in the index and 
     * the current AskAudience instance. 
     * 
     * setVotes is then called, passing in the instance's setPollVotes method, 
     * which passes in the boolean FFused condition, which checks if the parameter 
     * question and 50/50 question are the same, and the integer index. This 
     * method then calls one of the three Xvotes methods, passing in the same 
     * parameters, where X = easy/medium/hard.
     * 
     * After this, updateHashMap is called, which updates the hashmap keys' 
     * values with the corresponding number of votes.
     */
    @Override
    public void lifeline() 
    {
        //0 = A, 1 = B, 2 = C, 3 = D
        int index = 0;
        FFused = getFFquestion() == question;
        
        //idiomatic design pattern:
        for (String all : question.getAnswers())
        {
            if (!all.equals(question.getRightAns()))
                index++;
            else
                break;
        }
        
        //set all values to zero initially:
        answerVotes.put(question.getAnswers()[0], 0);
        answerVotes.put(question.getAnswers()[1], 0);
        answerVotes.put(question.getAnswers()[2], 0);
        answerVotes.put(question.getAnswers()[3], 0);
        
        do
        {
            option = getInteger().nextInt(4);

        } while (question.getAnswers()[option].equals(getFirstWrong()) || 
                question.getAnswers()[option].equals(getSecondWrong()) || 
                question.getAnswers()[option].equals(question.getRightAns()));
        
        AskAudienceVotes aav = new AskAudienceVotes(index, this);
        setVotes(aav.setPollVotes(isFFused(), index));
        updateHashMap(isFFused(), index);
    }
    
    /**
     * This method updates the hashmap values after the poll has been conducted
     * 
     * @param FFused
     * @param index 
     */
    private void updateHashMap(boolean FFused, int index)
    {
        //case 1: Ask Audience poll if the 50/50 lifeline hasn't been used; or 
        //has been used, but for a different question:
        if (!FFused)  //only activates if lifeline hasn't been used
        {
            //replace all values in the HashMap for all cases:
            int wrongOptions = 1;
            for (int i = 0; i < 4; i++)
            {
                if (question.getAnswers()[i].equals(question.getRightAns()))
                    answerVotes.replace(question.getAnswers()[i], getVotes()[0]);
                else
                    answerVotes.replace(question.getAnswers()[i], getVotes()[wrongOptions++]);
            }
        }
        //case 2: Ask Audience poll if the 50/50 lifeline has been used prior 
        //to the Ask Audience lifeline for the same question:
        else
        {
            answerVotes.replace(question.getAnswers()[option], getVotes()[option]);
            answerVotes.replace(question.getAnswers()[index], getVotes()[index]);
        }
    }

    /**
     * @return the FFused
     */
    public boolean isFFused() 
    {
        return FFused;
    }

    /**
     * @param FFused the FFused to set
     */
    public void setFFused(boolean FFused) 
    {
        this.FFused = FFused;
    }
}