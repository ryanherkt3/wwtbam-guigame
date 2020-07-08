package pdcguiproject;

import java.util.HashMap;
import java.util.Random;

/**
 * This Lifelines class is an abstract class with an abstract method for the 
 * implementation of the lifeline. The FiftyFifty, AskAudience and PhoneAFriend 
 * classes all use this by extending this class. This class has a constructor 
 * which sets questionNumber and fiftyFiftyQuestion to 16, and firstWrong and 
 * secondWrong to empty strings.
 * 
 * All the declared variables are encapsulated and are eligible to be accessed by 
 * any of the subclasses of this class (protected modifier).
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public abstract class Lifelines
{
    //set as protected access modifier to maximise code reuse:
    protected int questionNumber;     //when lifeline was used
    protected Random integer;  
    protected String result; //of the lifeline
    
    protected int[] votes;     //for Ask Audience
    protected HashMap<String, Integer> answerVotes;     //for Ask Audience
        
    protected int option; //to randomise the confidence level the friend has in their answer
    
    protected String firstWrong, secondWrong; //for the two incorrect answers
    protected Question question;
    protected Question FFquestion; //question 5050 lifeline used on

    /**
     * This default Lifelines constructor has no parameters, and initializes/defines 
     * the Random integer, used, integer questionNumber, firstWrong, 
     * secondWrong and answerVotes values.
     * 
     * questionNumber and fiftyFiftyQuestion are both set out of bounds to 16 as 
     * all the lifelines will be unused to start with.
     */
    public Lifelines()
    {
        this.questionNumber = 16;       //set deliberately out of bounds
        this.FFquestion = null;       //set deliberately out of bounds
        this.question = null;
        this.integer = new Random();
        this.votes = new int[4];
        this.answerVotes = new HashMap<>();
        this.firstWrong = "";
        this.secondWrong = "";
        this.result = "";
    }
    
    /**
     * This data encapsulation method returns the question where the lifeline 
     * was used
     * 
     * @return the question
     */
    public Question getQuestion() 
    {
        return question;
    }

    /**
     * This data encapsulation method sets the question for the lifeline to use
     * 
     * @param question the question to set
     */
    public void setQuestion(Question question) 
    {
        this.question = question;
    }
    
    /**
     * This abstract method invokes the algorithm for the lifeline. This ensures the 
     * lifelines can do operations which can either eliminate two wrong answers 
     * (50/50), give a confidence level for an answer (Phone A Friend), or poll 
     * the audience (Ask The Audience), where the correct answer is weighted to 
     * a higher random range than the other answers.
     */
    abstract protected void lifeline();

    /**
     * This data encapsulation method returns the questionNumber which 
     * the lifeline was used for
     * 
     * @return the questionNumber
     */
    public int getQuestionNumber() 
    {
        return questionNumber;
    }

    /**
     * This data encapsulation method sets the questionNumber
     * 
     * @param questionNumber the questionNumber to set
     */
    public void setQuestionNumber(int questionNumber) 
    {
        this.questionNumber = questionNumber;
    }
    
    /**
     * This data encapsulation method returns an instance of a Random variable
     * 
     * @return the integer
     */
    public Random getInteger() 
    {
        return integer;
    }

    /**
     * This data encapsulation method sets an instance of a Random variable
     * 
     * @param integer the integer to set
     */
    public void setInteger(Random integer) 
    {
        this.integer = integer;
    }
    
    /**
     * This data encapsulation method returns the option number the 'computer' 
     * takes when determining the two random wrong answers
     * 
     * @return option as int
     */
    public int getOption()
    {
        return option;
    }
    
    /**
     * This data encapsulation method sets the option number the 'computer' 
     * takes when determining the two random wrong answers
     * 
     * @param option as int
     */
    public void setOption(int option)
    {
        this.option = option;
    }
    
    /**
     * This data encapsulation method returns the first of two randomly 
     * selected wrong answers
     * 
     * @return firstWrong as String
     */
    public String getFirstWrong()
    {
        return firstWrong;
    }
    
    /**
     * This data encapsulation method sets the first of two randomly 
     * selected wrong answers
     * 
     * @param firstWrong as String
     */
    public void setFirstWrong(String firstWrong)
    {
        this.firstWrong = firstWrong;
    }
    
    /**
     * This data encapsulation method returns the second of two randomly 
     * selected wrong answers
     * 
     * @return secondWrong as String
     */
    public String getSecondWrong()
    {
        return secondWrong;
    }
    
    /**
     * This data encapsulation method sets the second of two randomly 
     * selected wrong answers
     * 
     * @param secondWrong as String
     */
    public void setSecondWrong(String secondWrong)
    {
        this.secondWrong = secondWrong;
    }

    /**
     * This data encapsulation method returns the result of a lifeline.
     * 
     * @return result as a String
     */
    public String getResult() 
    {
        return result;
    }

    /**
     * This data encapsulation method sets the result of a lifeline.
     * 
     * @param result as a String
     */
    public void setResult(String result) 
    {
        this.result = result;
    }

    /**
     * This data encapsulation method returns the votes array.
     * 
     * @return votes as an int array
     */
    public int[] getVotes() 
    {
        return votes;
    }

    /**
     * This data encapsulation method sets the votes array.
     * 
     * @param votes the votes to set
     */
    public void setVotes(int[] votes) 
    {
        this.votes = votes;
    }

    /**
     * This data encapsulation method returns the HashMap of answerVotes.
     * 
     * @return answerVotes as a HashMap
     */
    public HashMap<String, Integer> getAnswerVotes() 
    {
        return answerVotes;
    }

    /**
     * This data encapsulation method sets the HashMap of answerVotes.
     * 
     * @param answerVotes the HashMap to set
     */
    public void setAnswerVotes(HashMap<String, Integer> answerVotes) 
    {
        this.answerVotes = answerVotes;
    }

    /**
     * @return the FFquestion
     */
    public Question getFFquestion() {
        return FFquestion;
    }

    /**
     * @param FFquestion the FFquestion to set
     */
    public void setFFquestion(Question FFquestion) {
        this.FFquestion = FFquestion;
    }
}