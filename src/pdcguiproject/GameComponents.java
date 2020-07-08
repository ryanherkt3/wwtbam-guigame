package pdcguiproject;

import java.util.Stack;

/**
 * A class which holds all the primitive variables which make up the main 
 * components of the game. All variables have get methods, and the non-final 
 * variables have setter methods
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public class GameComponents 
{
    private final Stack<Integer> questionIndices;
    private int questionsCorrect;
    private final int[] MONEY_VALUES;
    private Question questionAsked;
    private final Lifelines FF;
    private final Lifelines AA;
    private final Lifelines PAF;
    
    /**
     * The default constructor for this class initializing all variables.
     */
    public GameComponents()
    {
        this.questionIndices = new Stack<>();
        this.questionsCorrect = 0;
        this.MONEY_VALUES = new int[]{100, 200, 300, 500, 
        1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 250000, 500000, 
        1000000};
        this.FF = new FiftyFifty();          //polymorphism
        this.AA = new AskAudience();         //polymorphism
        this.PAF = new PhoneAFriend();       //polymorphism
    }
    
    /**
     * @return the FF
     */
    public Lifelines getFF() {
        return FF;
    }

    /**
     * @return the AA
     */
    public Lifelines getAA() {
        return AA;
    }

    /**
     * @return the PAF
     */
    public Lifelines getPAF() {
        return PAF;
    }

    /**
     * @return the questionAsked
     */
    public Question getQuestionAsked() {
        return questionAsked;
    }

    /**
     * @return the questionsCorrect
     */
    public int getQuestionsCorrect() {
        return questionsCorrect;
    }

    /**
     * @return the MONEY_VALUES
     */
    public int[] getMONEY_VALUES() {
        return MONEY_VALUES;
    }

    /**
     * @return the questionIndices
     */
    public Stack<Integer> getQuestionIndices() {
        return questionIndices;
    }

    /**
     * @param questionsCorrect the questionsCorrect to set
     */
    public void setQuestionsCorrect(int questionsCorrect) {
        this.questionsCorrect = questionsCorrect;
    }

    /**
     * @param questionAsked the questionAsked to set
     */
    public void setQuestionAsked(Question questionAsked) {
        this.questionAsked = questionAsked;
    }
}