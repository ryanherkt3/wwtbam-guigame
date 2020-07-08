package pdcguiproject;

/**
 * This Questions class contains information and methods for each question. All 
 * Question variables have four possible answers (represented by a String array), 
 * a right answer (String) and a question (String). This class contains 
 * encapsulation (get & set) methods for all the variables listed above, a 
 * constructor to initialize each Question instance, and a toString method 
 * representing a question.
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public class Question
{
    private String question;
    private String[] answers;
    private String rightAns;

    /**
     * This Question constructor initializes a question, setting question to 
     * be the question String passed in, the answers array to be the four 
     * possible answers A, B, C and D, and rightAns to be whatever the right 
     * answer to the question is (out of A, B, C or D).
     * 
     * @param question
     * @param A
     * @param B
     * @param C
     * @param D
     * @param rightAns
     */
    public Question(String question, String A, String B, String C, String D, String rightAns)
    {
        this.question = question;
        this.answers = new String[]{A, B, C, D};
        this.rightAns = rightAns;
    }
    
    /**
     * This data encapsulation method sets the Question
     * 
     * @param question
     */
    public void setQuestion(String question) 
    {
        this.question = question;
    }
    
    /**
     * This data encapsulation method returns the four possible answers as an 
     * array
     * 
     * @return answers as an array
     */
    public String[] getAnswers() 
    {
        return answers;
    }

    /**
     * This data encapsulation method sets the answers array
     * 
     * @param answers the answers to set
     */
    public void setAnswers(String[] answers)
    {
        this.answers = answers;
    }
    
    /**
     * This data encapsulation method returns the correct answer (A, B, C, D)
     * 
     * @return right answer as String
     */
    public String getRightAns() 
    {
        return rightAns;
    }
    
    /**
     * This data encapsulation sets the right answer to the question
     * 
     * @param rightAns
     */
    public void setRightAns(String rightAns) 
    {
        this.rightAns = rightAns;
    }

    /**
     * This data encapsulation provides a toString representation of a question, 
     * simply printing out the question.
     * 
     * @return String as toString representation of a question
     */
    @Override
    public String toString()
    {
        return getQuestion();
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }
}