
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sun.audio.*;

/**
 * This class is for the implementation of the Scoreboard Screen, which displays the top five user highscores in the database
 * @authors Tian Lee, Paul Price and Christian Kennedy
 *
 */
public class ScoreboardScreen extends MenuOutline{


	private JPanel top_panel;
	private JPanel middle_panel;
	private JPanel lower_panel;
	
	/**
	 * This method is responsible for displaying the different panels in the frame as well as adding buttons and labels to the panels
	 */
	public void openMenu() {
		Container content_pane = getContentPane();	
		JFrame current_frame = this;
		top_panel = new PicPanel("trials.png");
		middle_panel = new PicPanel("trials2.png");
		lower_panel = new PicPanel("trials3.png");
		//add panels to content pane
		content_pane.add(top_panel);
		content_pane.add(middle_panel);
		content_pane.add(lower_panel);
		//*********
		JLabel blank1_label = createLabel("skinnyblank.png");	//blank label for formatting of panel
		top_panel.add(blank1_label);
		//*********
		//now want to display highscore table
		JLabel top_highscores = createLabel("top_highscores.png");
		//
		GameDatabase gd = new GameDatabase();
		String[] highscore_list = gd.createHighscoreList();
		JList score_table = new JList(highscore_list);
		score_table.setFont(new Font("Arial", Font.BOLD, 40));
		middle_panel.add(score_table);
		//*******
		JLabel blank_label = createLabel("blank.png");			//blank label for formatting of panel
		JLabel skinnyblank_label = createLabel("skinnyblank.png");
		top_panel.add(skinnyblank_label);
		top_panel.add(top_highscores);		
		//*******
		JButton return_button = createButton("returntomenu.png");
		return_button.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	current_frame.dispose();
		    	StartMenuScreen start_menu = new StartMenuScreen();
		    }
		});
		//**********
		lower_panel.add(blank_label);
		lower_panel.add(return_button);
	}
		

}
