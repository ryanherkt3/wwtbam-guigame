package pdcguiproject;

/**
 * This PhoneAFriend class contains the algorithm for the Phone A Friend lifeline 
 * (lifeline method). It also has a default constructor.
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public class PhoneAFriend extends Lifelines
{
    /**
     * This default PhoneAFriend constructor only calls the Lifelines superclass, 
     * as all variables are declared there in its constructor.
     */
    public PhoneAFriend() 
    {
        super();
    }
    
    /**
     * This method contains the algorithm for the Phone A Friend lifeline.
     * 
     * Algorithm steps:
     * 1) Confidence level integer is set to a random value between zero and 100
     * 2) An instance of PhoneAFriendPoll is called, which encourages 
     * polymorphism instead of the switch case which was in my PDC Project 1
     * 3) The confidence level determines what the instance in (2) is 
     * instantiated as
     * 4) setResult(...) is called, with verdict.result() being the parameter. 
     * Each instance of the PhoneAFriendPoll subclass has its own algorithm to 
     * determine what the friend's answer/opinion is.
     */
    @Override
    public void lifeline() 
    {
        int confidenceLevel = (getInteger().nextInt(11)) * 10;   //range 0-100;
        PhoneAFriendPoll verdict;
        
        if (confidenceLevel <= 30)  //0 to 30
            verdict = new PAFNotConfident(confidenceLevel, this);
        else if (confidenceLevel > 30 && confidenceLevel < 80)  //40 to 70
            verdict = new PAFSlightlyConfident(confidenceLevel, this);
        else    //80 to 100
            verdict = new PAFVeryConfident(confidenceLevel, this);
        
        setResult(verdict.result());
    }
}