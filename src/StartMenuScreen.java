
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/** 
 * This class is for the implementation of the Start Menu Screen, which gives the user
 * the option to Play, see Controls, see Highscores, return to Log In, or Exit to Desktop
 * @authors Tian Lee, Paul Price and Christian Kennedy
 *
 */
public class StartMenuScreen extends MenuOutline{
	
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
		//******
		JLabel blank_label = createLabel("skinnyblank.png");	//blank label for formatting of panel
		top_panel.add(blank_label);
		//*********
		JButton start_button = createButton("startgame.png");
		start_button.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	current_frame.dispose();
		    	AzimuthGame new_game = new AzimuthGame();
		    	new_game.start();
		    }
		}); 
		//********
		JButton controls_button = createButton("controls.png");
		controls_button.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	current_frame.dispose();
		    	ControlsMenuScreen controls_menu = new ControlsMenuScreen();
		    }
		});	
		//*********
		JButton highscores_button = createButton("highscores.png");
		highscores_button.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	current_frame.dispose();
		    	ScoreboardScreen scoreboard = new ScoreboardScreen();
		    }
		});	
		//********
		JButton exit_button = createButton("exit.png");
		exit_button.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	current_frame.dispose();
		    	current_frame.dispatchEvent(new WindowEvent(current_frame, WindowEvent.WINDOW_CLOSING));
	        }
	  	});		
		//*****
		JLabel blankline_label1 = createLabel("blankline.png");		//blank labels for formatting of panels
		JLabel blankline_label2 = createLabel("blankline.png");
		JLabel skinnyblank_label1 = createLabel("skinnyblank.png");
		JLabel skinnyblank_label2 = createLabel("skinnyblank.png");
		JLabel skinnyblank_label3 = createLabel("skinnyblank.png");
		//*******
		
		JButton logout_button = createButton("logout.png");
		logout_button.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	current_frame.dispose();
		    	LoginMenuScreen login_menu = new LoginMenuScreen();
	        }
	  	});		
		//*******
		top_panel.add(skinnyblank_label1);
		top_panel.add(start_button);
		middle_panel.add(blankline_label1);
		middle_panel.add(controls_button);
		middle_panel.add(skinnyblank_label2);
		middle_panel.add(highscores_button);
		lower_panel.add(blankline_label2);
		lower_panel.add(exit_button);
		lower_panel.add(skinnyblank_label3);
		lower_panel.add(logout_button);
	}
	

}
