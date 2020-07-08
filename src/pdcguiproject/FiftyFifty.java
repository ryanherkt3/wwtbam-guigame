package pdcguiproject;

/**
 * This FiftyFifty class contains the algorithm for the 50/50 lifeline (lifeline 
 * method). It also has a default constructor.
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public class FiftyFifty extends Lifelines
{
    /**
     * This default FiftyFifty constructor only calls the Lifelines superclass.
     */
    public FiftyFifty() 
    {
        super();
    }
    
    /**
     * This method contains the algorithm for the 50/50 lifeline. 
     * 
     * The algorithm sets secondRandom to 0 and initializes an allAnswers array, 
     * which contains the four options. Then a do-while loop runs until both the 
     * first wrong answer and the second wrong answer are not the correct 
     * answer. An inner do-while loop checks if secondRandom is equal to option, 
     * if so, this inner loop continues.
     * 
     * After the outer loop is exited, the result is set (formatting is in 
     * algorithm).
     */
    @Override
    public void lifeline()
    {
        String[] allAnswers = question.getAnswers();
        int secondRandom;
        
        //run if either of the eliminated options is the correct answer
        do
        {
            option = integer.nextInt(4);    //to determine 'first' wrong answer
            firstWrong = allAnswers[option];
            
            do
            {
                //to determine 'second' wrong answer
                secondRandom = integer.nextInt(4);
                secondWrong = allAnswers[secondRandom];
            } while(option == secondRandom);
            
        } while (firstWrong.equals(question.getRightAns()) || 
                secondWrong.equals(question.getRightAns()));
        
        //sets the lifeline result as the two wrong answers
        setResult("'" + firstWrong + "' and '" + secondWrong + "' are incorrect!");
    }
}