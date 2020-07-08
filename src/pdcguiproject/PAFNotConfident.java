package pdcguiproject;

/**
 * An subclass of the PhoneAFriendPoll class.
 * 
 * This class has its own constructor, and returns as a Phone A Friend result 
 * to the user that they don't know the answer.
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public class PAFNotConfident extends PhoneAFriendPoll
{
    /**
     * Default constructor calling the superclass
     * 
     * @param conf
     * @param friend 
     */
    public PAFNotConfident(int conf, PhoneAFriend friend) 
    {
        super(conf, friend);
    }
    
    /**
     * Overridden abstract method which returns to the user that the friend 
     * isn't sure of the answer.
     * 
     * @return String of the user's opinion
     */
    @Override
    protected String result() 
    {
        return "I'm not sure of the answer sorry.";
    }
}