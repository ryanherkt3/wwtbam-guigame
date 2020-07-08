package pdcguiproject;

import java.awt.*;
import javax.swing.*;

/**
 * This class extends JPanel, and updates the values of the results panel from 
 * the in-game GUI, instead of doing it from the main class. It contains an 
 * array of ProgressBars and Labels for the Ask Audience poll, as well as 
 * three separate labels for the text outputs of the lifelines. There are also 
 * getter methods (i.e. half the encapsulation methods) for all the variables 
 * in this class.
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public class ResultsPanel extends JPanel
{
    private JProgressBar[] bars;
    private JLabel[] votes;
    private JLabel FFquestion, AAquestion, PAFquestion;
    
    /**
     * Default constructor which sets up the ResultsPanel.
     */
    public ResultsPanel()
    {
        super();
        this.setLayout(new GridLayout(11,1));   //set the layout
        
        //initialize variables:
        this.bars = new JProgressBar[4];
        this.votes = new JLabel[4];
        
        FFquestion = new JLabel("50/50 unused, press the '50/50' button to "
                + "use it!");
        PAFquestion = new JLabel("Phone A Friend unused, press the 'Phone A "
                + "Friend' button to use it!");
        AAquestion = new JLabel("Ask the Audience unused, press the 'Ask the "
                + "Audience' button to use it!");
        add(FFquestion);
        add(PAFquestion);
        add(AAquestion);
        
        for (int k = 0; k < bars.length; k++)
        {
            bars[k] = new JProgressBar(0, 100);
            bars[k].setValue(0);
            bars[k].setStringPainted(false);
            //set votes foreground to purple:
            bars[k].setForeground(new Color(128, 0, 128));
        }
        for (int j = 0; j < votes.length; j++)
        {
            votes[j] = new JLabel("Answer: 0%");
            
            Font f = votes[j].getFont();
            votes[j].setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        }
        
        for (int i = 0; i < 4; i++)
        {
            add(votes[i]);
            add(bars[i]);
        }
        
        this.setVisible(true);
    }

    /**
     * @return the progress bars
     */
    public JProgressBar[] getBars() 
    {
        return bars;
    }

    /**
     * @return the Ask Audience votes
     */
    public JLabel[] getVotes() 
    {
        return votes;
    }

    /**
     * @return the FFquestion
     */
    public JLabel getFFquestion() 
    {
        return FFquestion;
    }

    /**
     * @return the AAquestion
     */
    public JLabel getAAquestion() 
    {
        return AAquestion;
    }

    /**
     * @return the PAFquestion
     */
    public JLabel getPAFquestion() 
    {
        return PAFquestion;
    }
}
