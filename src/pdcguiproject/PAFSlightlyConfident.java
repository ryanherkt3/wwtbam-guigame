package pdcguiproject;

/**
 * An subclass of the PhoneAFriendPoll class.
 * 
 * This class has its own constructor, and returns as a Phone A Friend result 
 * to the user that they don't know the answer.
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public class PAFSlightlyConfident extends PhoneAFriendPoll
{
    /**
     * Default constructor calling the superclass
     * 
     * @param conf
     * @param friend 
     */
    public PAFSlightlyConfident(int conf, PhoneAFriend friend) 
    {
        super(conf, friend);
    }
    
    /**
     * Overridden abstract method which returns returns to the user that the 
     * friend is 40 to 70% confident in what they think the answer is.
     * 
     * First though, the algorithm checks if the 50/50 lifeline has been used. 
     * If so, the integer option variable from the PhoneAFriend instance is set 
     * and reset until allAnswers[getFriend().option] isn't the first or second 
     * wrong answer.
     * 
     * @return String of the user's opinion
     */
    @Override
    protected String result() 
    {
        String[] allAnswers = getFriend().question.getAnswers();
        
        getFriend().option = getFriend().getInteger().nextInt(4);
        if (getFriend().getQuestion() == getFriend().getFFquestion())
        {
            do
            {
                getFriend().option = getFriend().getInteger().nextInt(4);

            } while (allAnswers[getFriend().option].equals(getFriend().getFirstWrong()) ||
                    allAnswers[getFriend().option].equals(getFriend().getSecondWrong()));
        }
        
        return "I'm " + super.getConfidence() + "% confident the answer "+ "is '" 
                + allAnswers[getFriend().option] + "'";
    }
}