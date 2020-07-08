package pdcguiproject;

/**
 * An subclass of the PhoneAFriendPoll class.
 * 
 * This class has its own constructor, and returns as a Phone A Friend result 
 * to the user that they are highly confident the answer is the correct one.
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public class PAFVeryConfident extends PhoneAFriendPoll
{
    /**
     * Default constructor calling the superclass
     * 
     * @param conf
     * @param friend 
     */
    public PAFVeryConfident(int conf, PhoneAFriend friend) 
    {
        super(conf, friend);
    }
    
    /**
     * Overridden abstract method which returns returns to the user that the 
     * friend is 80 to 100% confident in what they think the answer is (i.e. 
     * the right answer).
     * 
     * @return String of the user's opinion
     */
    @Override
    protected String result() 
    {
        return "I'm " + super.getConfidence() + "% confident the answer " + "is '" 
                + getFriend().question.getRightAns() + "'";
    }
}