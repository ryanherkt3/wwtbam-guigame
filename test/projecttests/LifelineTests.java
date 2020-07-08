package projecttests;

import java.util.Random;
import org.junit.*;
import pdcguiproject.AskAudience;
import pdcguiproject.FiftyFifty;
import pdcguiproject.PhoneAFriend;
import pdcguiproject.Question;

/**
 * A class which tests all the important functionalities of all the lifelines 
 * and their algorithms. These tests are done independently from the GUI / the 
 * game, with sample data inserted for the Question instances and for the 
 * lifeline instance being tested.
 * 
 * There are seven test cases:
 * === ASK AUDIENCE TEST CASES: === 
 * 1) Lifeline when 50/50 is not used on the question, we should expect the 
 * votes total to equal 100 and four options to be greater than zero
 * 2) Lifeline when 50/50 is used on the question, we should expect the 
 * votes total to equal 100,  and two options to be zero and two to be 
 * greater than zero
 * 3) Test the if condition that checks whether the 50/50 lifeline has been 
 * used on the question or not
 * === FIFTY FIFTY TEST CASES: ===
 * 4) Test how many answers the lifeline eliminates
 * 5) Test if either of the two answers it eliminates are the correct answer
 * 6) Test if the two answers the lifeline eliminates are the same
 * === PHONE A FRIEND TEST CASES: ===
 * 7) Lifeline when 50/50 is not used on the question, we should expect the 
 * result/poll to be non-null, where the friend provides us with an answer
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public class LifelineTests 
{
    private Question testQuestion;  //variable used in some of the tests
    
    /**
     * This method sets up the test class by initializing the test variable. 
     * It is called before the tests run.
     */
    @Before
    public void setUpTestVariable() 
    {
        this.testQuestion = new Question("1+1 is?", "1", "2", "3", "4", "2");
    }
    
    /**
     * This method 'tears down' the test class by 'nullifying' the test variable. 
     * It is called after the tests have been run.
     */
    @After
    public void tearDownTestVariable()
    {
        this.testQuestion = null;
    }
    
    /**
     * Test of lifeline method, of class AskAudience. This test tests the 
     * Lifeline when the 50/50 lifeline is not used on the question. In this 
     * test, we should expect the votes total to equal 100 and four options 
     * to be greater than zero (and sum up to 100)
     */
    @Test
    public void testAskAudienceFiftyFiftyUnused() 
    {
        System.out.println("testAskAudienceFiftyFiftyUnused() called");
        System.out.println("Testing Ask Audience lifeline, no 50/50 used");
        
        Random r = new Random();
        
        //class we're testing:
        AskAudience instance = new AskAudience();
        instance.setQuestionNumber(r.nextInt(16));
        instance.setQuestion(testQuestion);
        
        //method we want to test:
        instance.lifeline();
        
        int expectedVotePercentage = 100;   //expected value of all votes
        int actualVotePercentage = 0;
        
        //get actual value of all votes:
        for (int i = 0; i < instance.getVotes().length; i++)
        {
            actualVotePercentage += instance.getVotes()[i];
            System.out.println("Vote #" + (i+1) + ": " + instance.getVotes()[i]);
        }
        
        //test:
        Assert.assertEquals(expectedVotePercentage, actualVotePercentage);
        System.out.println("Value: " + actualVotePercentage);
        System.out.println();
    }
    
    /**
     * Test of lifeline method, of class AskAudience. This test tests the 
     * Lifeline when the 50/50 lifeline is used on the question. In this test, 
     * we should expect the votes total to equal 100, the two eliminated 
     * options to equal zero and the remaining two to be greater than zero (and 
     * sum up to 100).
     */
    @Test
    public void testAskAudienceFiftyFiftyUsedBeforehand() 
    {
        System.out.println("testAskAudienceFiftyFiftyUsedBeforehand() called");
        System.out.println("Testing Ask Audience lifeline, 50/50 used beforehand");
        
        //class we're testing:
        AskAudience instance = new AskAudience();
        instance.setFFquestion(testQuestion);
        instance.setQuestion(testQuestion);
        
        //method we want to test:
        instance.lifeline();
        
        int expectedVotePercentage = 100;   //expected value of all votes
        int actualVotePercentage = 0;
        
        //get actual value of all votes:
        for (int i = 0; i < instance.getVotes().length; i++)
        {
            actualVotePercentage += instance.getVotes()[i];
            System.out.println("Vote #" + (i+1) + ": " + instance.getVotes()[i]);
        }
        
        //test:
        Assert.assertEquals(expectedVotePercentage, actualVotePercentage);
        System.out.println("Value: " + actualVotePercentage);
        System.out.println();
    }
    
    /**
     * Test of lifeline method, of class AskAudience. This test tests the 
     * the if condition that checks whether the 50/50 lifeline has been used 
     * on the question or not. It does this by assigning a random number to 
     * the setQuestionNumber of the class, then assigns the same number 
     * to the setFiftyFiftyQuestion method.
     */
    @Test
    public void testAskAudienceFiftyFiftyIsUsedValidation() 
    {
        System.out.println("testAskAudienceFiftyFiftyIsUsedValidation() called");
        System.out.println("Testing for the Ask Audience lifeline, 50/50 used "
                + "beforehand. Testing if 50/50 question is same as AA "
                + "question check works");
        
        Random r = new Random();
        
        //class we're testing:
        AskAudience instance = new AskAudience();
        
        //methods we want to test:
        instance.setQuestionNumber(r.nextInt(15)+1);
        instance.setFFquestion(testQuestion);
        
        //test:
        System.out.println("Question #: " + instance.getQuestionNumber() + 
                    ". 50/50 Question #: " + instance.getFFquestion());
        Assert.assertTrue(instance.getFFquestion() == testQuestion);
        System.out.println();
    }
    
    /**
     * Test of lifeline method, of class FiftyFifty. This test tests the 
     * Lifeline for how many answers it eliminates, using assertEquals to 
     * compare the expected value with the actual value.
     */
    @Test
    public void testFiftyFiftyNumberEliminatedIsTwo() 
    {
        System.out.println("testFiftyFiftyNumberEliminatedIsTwo() called");
        System.out.println("Testing 50/50 lifeline, how many answers were "
                + "eliminated?");
               
        //class we're testing:
        FiftyFifty instance = new FiftyFifty();
        
        //method we want to test:
        instance.setQuestion(testQuestion);
        instance.lifeline();
        
        int numElmininated = 2;   //expected number of answers eliminated
        String[] eliminated = new String[]{instance.getFirstWrong(), 
            instance.getSecondWrong()};
        
        //value we want to test / the test:
        Assert.assertEquals(numElmininated, eliminated.length);
        System.out.println("Number eliminated: " + eliminated.length);
        System.out.println();
    }
    
    /**
     * Test of lifeline method, of class FiftyFifty. This test tests the 
     * Lifeline and checks if the two answers it eliminates equal the correct 
     * answer, using assertFalse to compare both eliminated to the correct 
     * answer.
     */
    @Test
    public void testFiftyFiftyCorrectAnswerEliminated() 
    {
        System.out.println("testFiftyFiftyCorrectAnswerEliminated() called");
        System.out.println("Testing 50/50 lifeline, was the correct answer "
                + "eliminated?");
        
        //class we're testing:
        FiftyFifty instance = new FiftyFifty();
        
        //method we want to test:
        instance.setQuestion(testQuestion);
        instance.lifeline();
        
        // the test:
        Assert.assertFalse(instance.getFirstWrong().equals(testQuestion.getRightAns()) 
                && instance.getSecondWrong().equals(testQuestion.getRightAns()));
        System.out.println("Eliminated answer #1: " + instance.getFirstWrong() + 
                ". Eliminated answer #2: " + instance.getSecondWrong() + 
                ". Correct answer: " + testQuestion.getRightAns());
        System.out.println();
    }
    
    /**
     * Test of lifeline method, of class FiftyFifty. This test tests the 
     * Lifeline and checks if the two answers it eliminates are the same, using 
     * assertFalse to compare them both.
     */
    @Test
    public void testFiftyFiftyAreAnswersEliminatedTheSame() 
    {
        System.out.println("testFiftyFiftyAreAnswersEliminatedTheSame() called");
        System.out.println("Testing 50/50 lifeline, are both eliminated "
                + "answers the same?");
        
        //class we're testing:
        FiftyFifty instance = new FiftyFifty();
        
        //method we want to test:
        instance.setQuestion(testQuestion);
        instance.lifeline();
        
        // the test:
        Assert.assertFalse(instance.getFirstWrong().equals(instance.getSecondWrong()));
        System.out.println("Eliminated answer #1: " + instance.getFirstWrong() + 
                ". Eliminated answer #2: " + instance.getSecondWrong());
        System.out.println();
    }
    
    /**
     * Test of lifeline method, of class PhoneAFriend. This test tests the 
     * Lifeline when the 50/50 lifeline is not used on the question. In this 
     * test, we should expect the result/poll to be non-null, where the friend 
     * provides us with an answer
     */
    @Test
    public void testPhoneAFriendFiftyFiftyUnused() 
    {
        System.out.println("testPhoneAFriendFiftyFiftyUnused() called");
        System.out.println("Testing Phone A Friend lifeline, no 50/50 used");
        
        //class we're testing:
        PhoneAFriend instance = new PhoneAFriend();
        
        //method we want to test:
        instance.setQuestion(testQuestion);
        instance.lifeline();
        
        //test:
        Assert.assertNotNull(instance.getResult());
        System.out.println(instance.getResult());
        System.out.println();
    }
}