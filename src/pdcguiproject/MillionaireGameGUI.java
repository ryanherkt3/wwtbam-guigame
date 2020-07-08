package pdcguiproject;

//Capture all required imports:
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * The main class, which creates and shows the GUI (by calling the constructor), 
 * updates the question and four possible answers for the user and performs 
 * actions based on the button the user presses via the actionPerformed method.
 * There are getters and setters for all the 'private' attributes, except for 
 * the database attributes.
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public class MillionaireGameGUI extends JFrame implements ActionListener
{
    //these DB classes perform operations on the embedded database. These 
    //include getting questions, getting the last player's stats and inserting 
    //the stats of the current player into the DB after the game ends. These 
    //are all multithreaded operations as they get a connection from the 
    //DBManager class:
    private PlayerStatDBOperations playerStatTable; //a DB element
    private QuestionDBOperations questionTable; //another DB element
    
    private GameComponents gameComponents;
    private final ResultsPanel results;
    private final LifelinesPanel lifelinePanel;
    
    JButton ansA = new JButton(), ansB = new JButton(), ansC = new JButton(), 
            ansD = new JButton();
    JPanel answers, money;
    
    JPanel newGame;
    JButton yesButton, noButton;
    JLabel prompt;
    
    JPanel top;
    JLabel helper = new JLabel();
    
    JPanel game = new JPanel(new BorderLayout());
    JLabel question = new JLabel();
    
    JLabel[] values = new JLabel[15];
    JTabbedPane tabbedPane = new JTabbedPane();
    
    /**
     * The default constructor which initializes the embedded DB, as well as 
     * most of the GUI components above and the other non-GUI variables. 
     * These GUI components are added to the appropriate panels, and the 
     * panels are added to three tabbed panes, which gives the user efficient 
     * access to the elements of the game which they want to access (either 
     * the game, the results of the lifelines, or the money tree - which is 
     * very similar in format to the one seen on TV)
     */
    public MillionaireGameGUI()
    {
        this.playerStatTable = new PlayerStatDBOperations();
        this.questionTable = new QuestionDBOperations();
        this.gameComponents = new GameComponents();
        /*-------------------------*/
        top = new JPanel(new GridLayout(2,1));
        
        top.add(question);
        helper.setText("");
        top.add(helper);
        /*-------------------------*/
        answers = new JPanel(new GridLayout(2,2));
        
        ansA.addActionListener(this);
        ansB.addActionListener(this);
        ansC.addActionListener(this);
        ansD.addActionListener(this);
        
        //set background colour of answer buttons to sky blue:
        ansA.setBackground(new Color(135, 206, 235));
        ansB.setBackground(new Color(135, 206, 235));
        ansC.setBackground(new Color(135, 206, 235));
        ansD.setBackground(new Color(135, 206, 235));
                
        answers.add(ansA);
        answers.add(ansB);
        answers.add(ansC);
        answers.add(ansD);
        /*-------------------------*/
        money = new JPanel(new GridLayout(15,1));
        
        for (int i = 0; i < gameComponents.getMONEY_VALUES().length; ++i)
        {
            if (i < 9)
                values[i] = new JLabel("  " + (i+1) + ". $" + Integer.toString(
                        getGameComponents().getMONEY_VALUES()[i]));
            else
                values[i] = new JLabel((i+1) + ". $" + Integer.toString(
                        getGameComponents().getMONEY_VALUES()[i]));
            
            //set safety net colours to gold:
            if (i == 4 || i == 9 || i == 14)
                values[i].setForeground(new Color(212, 172, 55));
            
            money.add(values[i]);
        }
        /*-------------------------*/
        newGame = new JPanel();
        
        prompt = new JLabel("GAME IN PROGRESS!");
        yesButton = new JButton("Yes");
        noButton = new JButton("No");
        
        yesButton.addActionListener(this);
        noButton.addActionListener(this);
        yesButton.setEnabled(false);
        noButton.setEnabled(false);
        
        newGame.add(prompt);
        newGame.add(yesButton);
        newGame.add(noButton);
        /*-------------------------*/
        newQuestion();
        results = new ResultsPanel();
        lifelinePanel = new LifelinesPanel(this);
        /*-------------------------*/
        game.add(top, BorderLayout.NORTH);
        game.add(answers, BorderLayout.CENTER);
        game.add(lifelinePanel, BorderLayout.SOUTH);
        tabbedPane.addTab("Game", game);
        tabbedPane.addTab("Lifeline Results", results);
        tabbedPane.addTab("Money Tree", money);
        tabbedPane.addTab("Play Again", newGame);
        /*-------------------------*/
        add(tabbedPane);
    }
    
    /**
     * This method performs actions within the game based on the button the 
     * user presses, with two cases: 1: where the user presses an 
     * answer button (which is either the correct answer or the wrong answer); 
     * 2: the user is asked if they want to play again.
     * 
     * Note that the LifelinesPanel performs the actions on the game if a user 
     * wants to use a lifeline or walk away (i.e. presses one of the four 
     * buttons from that panel).
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        
        //create instance of multithread operations if required
        MultiThreadOperations operations = new MultiThreadOperations(
                getGameComponents().getQuestionsCorrect());
        
        if (source == yesButton || source == noButton)
        {
            this.dispose(); //close current window & dispose of all resources
            if (source == yesButton)
            {
                //create new GUI/game instance:
                MillionaireGameGUI millionaire = new MillionaireGameGUI();
                createAndShowGUI(millionaire);  //create and show new game
            }
        }
        
        if (source == ansA && !gameComponents.getQuestionAsked().getRightAns().equals(ansA.getText()) ||
            source == ansB && !gameComponents.getQuestionAsked().getRightAns().equals(ansB.getText()) ||
            source == ansC && !gameComponents.getQuestionAsked().getRightAns().equals(ansC.getText()) ||
            source == ansD && !gameComponents.getQuestionAsked().getRightAns().equals(ansD.getText()))
        {
            //game is over so clear/disable/enable the functionalities of 
            //the game:
            ansA.setEnabled(false);
            ansB.setEnabled(false);
            ansC.setEnabled(false);
            ansD.setEnabled(false);
            yesButton.setEnabled(true);
            noButton.setEnabled(true);
            lifelinePanel.setVisible(false);

            //show user appropriate messages #1:
            String unlucky = "Unlucky! You have won $";
            if (getGameComponents().getQuestionsCorrect() < 5)
            {
                question.setText(unlucky + "0!");
                operations.recordPlayerStats(0);
            }
            else if (getGameComponents().getQuestionsCorrect() < 10)
            {
                operations.recordPlayerStats(getGameComponents().getMONEY_VALUES()[4]);
                question.setText(unlucky + getGameComponents().getMONEY_VALUES()[4]);
            }
            else if (getGameComponents().getQuestionsCorrect() < 15)
            {
                operations.recordPlayerStats(getGameComponents().getMONEY_VALUES()[9]);
                question.setText(unlucky + getGameComponents().getMONEY_VALUES()[9]);
            }

            //set money lost to red:
            if (getGameComponents().getQuestionsCorrect() > 0)
                for (int i = getGameComponents().getQuestionsCorrect(); i%5 != 0; --i)
                    values[i-1].setForeground(Color.red);
            
            //show user appropriate messages #2:
            helper.setText("The correct answer was: '" + getGameComponents().getQuestionAsked().
                    getRightAns() + "'. Please visit the 'Play Again' tab");
            prompt.setText("Would you like to play again?");
        }
        
        if(source == ansA && getGameComponents().getQuestionAsked().getRightAns().equals(ansA.getText()) ||
            source == ansB && getGameComponents().getQuestionAsked().getRightAns().equals(ansB.getText()) ||
            source == ansC && getGameComponents().getQuestionAsked().getRightAns().equals(ansC.getText()) ||
            source == ansD && getGameComponents().getQuestionAsked().getRightAns().equals(ansD.getText()))
        {
            //set label of money just earnt to dark green:
            values[getGameComponents().getQuestionsCorrect()].setForeground(new Color(5, 102, 8));
            getGameComponents().setQuestionsCorrect(getGameComponents().getQuestionsCorrect()+1);
            
            if (getGameComponents().getQuestionsCorrect() < 15)
            {
                if (getGameComponents().getQuestionsCorrect() % 5 == 0)
                    helper.setText("Congratulations! You have reached safety "
                            + "net #" + getGameComponents().getQuestionsCorrect()/5 + "!");
                else
                    helper.setText("");
                
                //make all answers visible again if 50/50 has been used:
                if (!ansA.isVisible())
                    ansA.setVisible(true);
                if (!ansB.isVisible())
                    ansB.setVisible(true);
                if (!ansC.isVisible())
                    ansC.setVisible(true);
                if (!ansD.isVisible())
                    ansD.setVisible(true);
                
                //show the user the next question:
                newQuestion();
            }
            else
            {
                //game is over so clear the main functionalities of the game:
                answers.setVisible(false);
                lifelinePanel.setVisible(false);
                
                //show user appropriate messages:
                question.setText("CONGRATULATIONS! You have won $1000000! Well done!");
                helper.setText("Please visit the 'Play Again' tab");
                
                //ask user if they'd like to play again
                yesButton.setEnabled(true);
                noButton.setEnabled(true);
                prompt.setText("Would you like to play again?");
                
                //special override case, reset questions correct (as it will 
                //be 14, but should be 15):
                operations.setQuestionsCorrect(getGameComponents().getQuestionsCorrect());
                operations.recordPlayerStats(1000000);
            }
        }
    }
    
    /**
     * This class uses the JDBC to generate a new question for the game at the 
     * beginning of the game, or after the user gets an answer correct. It 
     * first gets the next index for the questionTable DB component to call, 
     * then makes the call from questionTable to get the question at the 
     * specified index.
     */
    public void newQuestion()
    {
        getNextIndex();
        
        //show stats of last player to user; call at beginning of game:
        if (getGameComponents().getQuestionsCorrect() == 0)
        {
            int[] lastStats = playerStatTable.getLastPlayersStats();
            helper.setText("Stats of previous player - Questions correct: " + 
                    lastStats[0] + ", Money won: $" + lastStats[1]);
        }

        questionTable.setID(getGameComponents().getQuestionIndices().peek());
        questionTable.setQuestion();
        getGameComponents().setQuestionAsked(questionTable.getQuestion());
        
        modifyQandA(new Random());
    }
    
    /**
     * Generates a random index for the next question for the game. 
     * It checks if it is in the stack - if it is, the index is generated again 
     * and again until a unique one not in the stack is found. This unique index 
     * is then added on the stack. The adjuster integers change depending on 
     * the difficulty of the question, the array is set via the method 
     * determineAdjustersForNextIndex.
     */
    private void getNextIndex()
    {
        Random rand = new Random();
        int index = 0;
        int adjusters[] = determineAdjustersForNextIndex();
        
        do
        {
            index = rand.nextInt(adjusters[0])+adjusters[1];
        } while (getGameComponents().getQuestionIndices().contains(index));
        
        getGameComponents().getQuestionIndices().push(index);
    }
    
    /**
     * Determines the adjusters to use for the random index, which is used to 
     * grab the next question from the embedded DB. The individual adjuster 
     * integers change depending on the difficulty of the question
     * 
     * @return int array of the two adjusters
     */
    private int[] determineAdjustersForNextIndex()
    {
        int[] adjusters = new int[2];
        
        //Set/reset the question via DB element:
        if (getGameComponents().getQuestionsCorrect() < 5)  //1 to 50 
        {
            adjusters[0] = 50;
            adjusters[1] = 1;
        }
        else if (getGameComponents().getQuestionsCorrect() < 10)  //51 to 100
        {
            adjusters[0] = 50;
            adjusters[1] = 51;
        }
        else    //101 to 140
        {
            adjusters[0] = 40;
            adjusters[1] = 101;
        }
        
        return adjusters;
    }
    
    /**
     * This method modifies the text for the answer buttons and question label, 
     * as well as setting the answers of the question according to the order in 
     * which they were randomized
     * 
     * @param rand 
     */
    private void modifyQandA(Random rand)
    {
        //Update text on answer buttons randomly. Run until each button has 
        //a unique (or distinct) text value:
        do
        {
            ansA.setText(getGameComponents().getQuestionAsked().getAnswers()[rand.nextInt(4)]);
            ansB.setText(getGameComponents().getQuestionAsked().getAnswers()[rand.nextInt(4)]);
            ansC.setText(getGameComponents().getQuestionAsked().getAnswers()[rand.nextInt(4)]);
            ansD.setText(getGameComponents().getQuestionAsked().getAnswers()[rand.nextInt(4)]);
        }while (ansA.getText().equals(ansB.getText()) || 
                ansA.getText().equals(ansC.getText()) || 
                ansA.getText().equals(ansD.getText()) || 
                ansB.getText().equals(ansC.getText()) ||
                ansB.getText().equals(ansD.getText()) ||
                ansC.getText().equals(ansD.getText()));
        
        //reset answers array of the current question according to the order 
        //they appear when looking at the GUI (top left -> bottom right):
        getGameComponents().getQuestionAsked().setAnswers(new String[]{
            ansA.getText(), ansB.getText(), ansC.getText(), ansD.getText()});
        
        //Update text on question and helper labels:
        question.setText((getGameComponents().getQuestionsCorrect()+1) + ") " + 
                getGameComponents().getQuestionAsked());
    }
    
    /**
     * Main method. Initializes the GUI and gets the game started by making a 
     * call to the newQuestion() method, which fills the text in for the 
     * question label and the four answer buttons.
     * 
     * @param args 
     */
    public static void main(String args[])
    {
        MillionaireGameGUI millionaire = new MillionaireGameGUI();
        createAndShowGUI(millionaire);
    }
    
    /**
     * Helper method to create and show GUI for the game
     * 
     * @param millionaire game instance
     */
    private static void createAndShowGUI(MillionaireGameGUI millionaire)
    {
        millionaire.setSize(600,300);
        millionaire.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        millionaire.setTitle("Who Wants To Be A Millionaire");
        millionaire.setVisible(true);
        
        // position the frame in the middle of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = millionaire.getSize();
        millionaire.setLocation((screenDimension.width-frameDimension.width)/2,
           (screenDimension.height-frameDimension.height)/2);
    } 

    /**
     * @return the resultsPanel
     */
    public ResultsPanel getResults() 
    {
        return results;
    }
    
    /**
     * @return the lifelinePanel
     */
    public LifelinesPanel getLifelinePanel() 
    {
        return lifelinePanel;
    }

    /**
     * @return the gameComponents
     */
    public GameComponents getGameComponents() 
    {
        return gameComponents;
    }
}