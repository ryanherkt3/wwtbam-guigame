package pdcguiproject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class extends JPanel, and executes the lifeline algorithms (or stops 
 * the game) instead of doing it from the main class. It contains four 
 * JButtons (for the three lifelines, and the walk option), as well an instance 
 * of the game (i.e. the main class) to achieve all the required operations. 
 * None of the variables in this class are encapsulated.
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public class LifelinesPanel extends JPanel implements ActionListener
{
    private JButton fiftyFifty, askAudience, phoneFriend, walk;
    private MillionaireGameGUI game;
    
    /**
     * Default constructor which sets up the LifelinesPanel.
     * 
     * @param game the instance of the main class
     */
    public LifelinesPanel(MillionaireGameGUI game)
    {
        super();
        this.game = game;
        this.setLayout(new GridLayout(1,4));
        
        fiftyFifty = new JButton("50/50");
        askAudience = new JButton("Ask the Audience"); 
        phoneFriend = new JButton("Phone a Friend"); 
        walk = new JButton("Walk");
        fiftyFifty.addActionListener(this);
        askAudience.addActionListener(this);
        phoneFriend.addActionListener(this);
        walk.addActionListener(this);
        
        //set background colour of lifeline buttons to orange:
        fiftyFifty.setBackground(Color.ORANGE);
        askAudience.setBackground(Color.ORANGE);
        phoneFriend.setBackground(Color.ORANGE);
        //set background colour of walk button to light gray:
        walk.setBackground(Color.YELLOW);
        
        add(fiftyFifty);
        add(askAudience);
        add(phoneFriend);
        add(walk);
        
        this.setVisible(true);
    }
    
    /**
     * This method performs actions within the game based on the button the 
     * user presses, with two distinct cases: the user presses 'walk', or the 
     * user presses one of the three lifeline buttons.
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Check order:
        //1. Does the user want to use a lifeline? If not...
        //2. Did the user walk, or is their answer wrong? If not...
        //3. The user's answer must be correct
        Object source = e.getSource();
        
        //create instance of multithread operations if required
        MultiThreadOperations operations = new MultiThreadOperations(
                game.getGameComponents().getQuestionsCorrect());
        
        if (source == walk)
        {
            //game is over so clear or disable the main functionalities of 
            //the game:
            game.ansA.setEnabled(false);
            game.ansB.setEnabled(false);
            game.ansC.setEnabled(false);
            game.ansD.setEnabled(false);
            game.getLifelinePanel().setVisible(false);
            
            //show user correct answer:
            game.helper.setText("The correct answer was: '" + game.getGameComponents().getQuestionAsked().
                    getRightAns() + "'. Please visit the 'Play Again' tab");
            game.prompt.setText("Would you like to play again?");
            game.yesButton.setEnabled(true);
            game.noButton.setEnabled(true);

            //show user amount of money won:
            if (game.getGameComponents().getQuestionsCorrect() == 0)
            {
                game.question.setText("You have won $0");
                operations.recordPlayerStats(0);
            }
            else
            {
                game.question.setText("Congratulations! You have "
                        + "won $" + game.getGameComponents().getMONEY_VALUES()
                                [game.getGameComponents().getQuestionsCorrect()-1]);

                operations.recordPlayerStats(game.getGameComponents().getMONEY_VALUES()
                        [game.getGameComponents().getQuestionsCorrect()-1]);
            }
        }
            
        if (source == fiftyFifty || source == askAudience || source == phoneFriend)
        {
            game.helper.setText("Please visit the Lifeline Results tab");
                        
            if (source == fiftyFifty)
            {
                operations.recordLifelineUsage("Fifty Fifty");
                
                //update variables within lifeline:
                game.getGameComponents().getFF().setQuestion(
                        game.getGameComponents().getQuestionAsked());
                game.getGameComponents().getAA().
                        setFFquestion(game.getGameComponents().getQuestionAsked());
                game.getGameComponents().getPAF().
                        setFFquestion(game.getGameComponents().getQuestionAsked());
                
                //show lifeline results:
                game.getGameComponents().getFF().lifeline();
                game.getResults().getFFquestion().setText(("50/50: " + 
                        (game.getGameComponents().getQuestionsCorrect()+1) 
                        + ") " + game.getGameComponents().getFF().getResult()));
                
                //special override case:
                game.helper.setText("50/50: " + game.getGameComponents().getFF().getResult());
                
                if (game.getGameComponents().getFF().getFirstWrong().equals(game.ansA.getText()) || 
                        game.getGameComponents().getFF().getSecondWrong().equals(game.ansA.getText()))
                    game.ansA.setVisible(false);
                if (game.getGameComponents().getFF().getFirstWrong().equals(game.ansB.getText()) || 
                        game.getGameComponents().getFF().getSecondWrong().equals(game.ansB.getText()))
                    game.ansB.setVisible(false);
                if (game.getGameComponents().getFF().getFirstWrong().equals(game.ansC.getText()) || 
                        game.getGameComponents().getFF().getSecondWrong().equals(game.ansC.getText()))
                    game.ansC.setVisible(false);
                if (game.getGameComponents().getFF().getFirstWrong().equals(game.ansD.getText()) || 
                        game.getGameComponents().getFF().getSecondWrong().equals(game.ansD.getText()))
                    game.ansD.setVisible(false);
                
                //show to user lifeline is no longer usable:
                disableLifeline(fiftyFifty);
            }
            if (source == askAudience)
            {
                operations.recordLifelineUsage("Ask Audience");
                game.getResults().getAAquestion().setText(((
                        game.getGameComponents().getQuestionsCorrect()+1) + ") " 
                        + game.getGameComponents().getQuestionAsked()));
                
                //update variables within lifeline:
                updateLifelineAttributes(game.getGameComponents().getAA());
                
                //call and show lifeline results:
                for (int j = 0; j < game.getResults().getVotes().length; j++)
                    game.getResults().getVotes()[j].setText(
                        game.getGameComponents().getQuestionAsked().getAnswers()[j] 
                        + ": " + game.getGameComponents().getAA().getAnswerVotes().
                        get(game.getGameComponents().getQuestionAsked().getAnswers()[j]) + "%");
                
                for (int k = 0; k < game.getResults().getBars().length; k++)
                    game.getResults().getBars()[k].setValue(
                            game.getGameComponents().getAA().getAnswerVotes().get
                        (game.getGameComponents().getQuestionAsked().getAnswers()[k]));
                
                //show to user lifeline is no longer usable:
                disableLifeline(askAudience);
            }
            if (source == phoneFriend)
            {
                operations.recordLifelineUsage("Phone A Friend");
                
                //update variables within lifeline:
                updateLifelineAttributes(game.getGameComponents().getPAF());
                
                game.getResults().getPAFquestion().setText(("Phone A Friend: " + 
                        (game.getGameComponents().getQuestionsCorrect()+1) 
                        + ") " + game.getGameComponents().getPAF().getResult()));
                
                //show to user lifeline is no longer usable:
                disableLifeline(phoneFriend);
            }
        }
    }
    
    /**
     * Helper method to show to user lifeline is used
     * 
     * @param jb JButton to perform operations on
     */
    private void disableLifeline(JButton jb)
    {
        jb.setBackground(Color.lightGray);
        jb.setEnabled(false);
    }
    
    /**
     * Helper method to update attributes inside the Lifeline variables (Ask 
     * the Audience and Phone A Friend only.
     * 
     * @param lifeline to update attributes for
     */
    private void updateLifelineAttributes(Lifelines lifeline)
    {
        lifeline.setQuestion(game.getGameComponents().getQuestionAsked());
        lifeline.setQuestionNumber(game.getGameComponents().getQuestionsCorrect());
        if (lifeline.getQuestion() == game.getGameComponents().getFF().getQuestion())
        {
            lifeline.setFirstWrong(game.getGameComponents().getFF().getFirstWrong());
            lifeline.setSecondWrong(game.getGameComponents().getFF().getSecondWrong());
        }
        //call and show lifeline:
        lifeline.lifeline();
    }
}