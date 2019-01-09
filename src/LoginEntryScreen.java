
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * This class implements the Login Entry screen, where users can enter their existing username
 * @authors Tian Lee, Paul Price, Christian Kennedy
 *
 */
public class LoginEntryScreen extends MenuOutline{
	
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
		JLabel enter_username_label = createLabel("enterusername.png");
		middle_panel.add(enter_username_label);
		//*********
		JTextField new_username_field = new JTextField(20);
		//*******
		JLabel blank_label = createLabel("blank.png");		//blank label for formatting of panel
		//******
		JButton enter_username = createButton("login.png");
		enter_username.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	GameDatabase us = new GameDatabase();
		    	String user_input = new_username_field.getText();
		    	//somehow need to save this username and then use it again after playing the game to update score
		    	PostGameScreen.username = user_input;
		    	boolean username_exists = us.findUser(user_input);
		    	if (username_exists) {
			    	current_frame.dispose();
			    	StartMenuScreen start_menu = new StartMenuScreen();
		    	} else {
			    	current_frame.dispose();
			    	InvalidUsernameScreen invalid_username = new InvalidUsernameScreen();
		    	}
		    }
		});
		//*******
		JButton return_button = createButton("returntomenu.png");
		return_button.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	current_frame.dispose();
		    	LoginMenuScreen login_menu = new LoginMenuScreen();
		    }
	  	});	
		//**********
		middle_panel.add(new_username_field);
		lower_panel.add(enter_username);
		lower_panel.add(blank_label);
		lower_panel.add(return_button);
	}
		

}
