package pdcguiproject;

/**
 * An extracted class (from PhoneAFriend class) which can determine the result 
 * of the PhoneAFriend poll for a particular question. An instance of 
 * PhoneAFriend is created, which reduces the number of primitives created 
 * in this class. These are both fully encapsulated
 * 
 * This class also removes the switch case (code smell) which was previously 
 * in the PhoneAFriend class (in PDC Project 1). It also means all methods in 
 * this class, its subclasses and the PhoneAFriend class are at least under 20 
 * lines, making maintainability of the codes easier.
 * 
 * This class is also an abstract class with an abstract method for the 
 * implementation of the result. The PAFNotConfident, PAFSlightlyConfident and 
 * PAFVeryConfident classes all use this by extending this class. The abstract 
 * method returns a unique string to the user.
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public abstract class PhoneAFriendPoll 
{
    protected int confidence;
    protected PhoneAFriend friend;
    
    /**
     * Default constructor initializing the confidence value and PhoneAFriend 
     * variable.
     * 
     * @param conf
     * @param friend 
     */
    public PhoneAFriendPoll(int conf, PhoneAFriend friend)
    {
        this.confidence = conf;
        this.friend = friend;
    }
    
    /**
     * The abstract method which all subclasses implement, determining and 
     * returning the result of the friend's confidence in an answer for a 
     * particular question
     * 
     * @return String Phone A Friend result
     */
    abstract protected String result();

    /**
     * @return the confidence
     */
    public int getConfidence() 
    {
        return confidence;
    }

    /**
     * @param confidence the confidence to set
     */
    public void setConfidence(int confidence) 
    {
        this.confidence = confidence;
    }

    /**
     * @return the friend
     */
    public PhoneAFriend getFriend() 
    {
        return friend;
    }

    /**
     * @param friend the friend to set
     */
    public void setFriend(PhoneAFriend friend) 
    {
        this.friend = friend;
    }
}