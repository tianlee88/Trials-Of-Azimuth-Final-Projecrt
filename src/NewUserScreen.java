
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
 * This class implements the New User screen, which appears when a user wants to add a new username to the database
 * @authors Tian Lee, Paul Price, Christian Kennedy
 *
 */
public class NewUserScreen extends MenuOutline{

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
		JLabel enter_username_label = createLabel("enterdesiredusername.png");
		middle_panel.add(enter_username_label);
		//*********
		JTextField new_username_field = new JTextField(20);
		middle_panel.add(new_username_field);
		//*******
		JLabel blank_label = createLabel("blank.png");		//blank label for formatting of panel
		//******
		JButton signup_button = createButton("signup.png");
		signup_button.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	GameDatabase us = new GameDatabase();
		    	String user_input = new_username_field.getText();
		    	if (user_input.length() < 3 || user_input.substring(0, 1).equals(" ")) {
		    		current_frame.dispose();
			    	BadEntryScreen bad_entry = new BadEntryScreen();
		    	} else {
			    	boolean username_taken = us.findUser(user_input);
			    	if (!username_taken) {
			    		us.addUser(user_input, 0);
				    	PostGameScreen.username = user_input;
				    	current_frame.dispose();
				    	StartMenuScreen start_menu = new StartMenuScreen();
			    	} else {
				    	current_frame.dispose();
				    	UsernameTakenScreen username_unavailable = new UsernameTakenScreen();
			    	}
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
		lower_panel.add(signup_button);
		lower_panel.add(blank_label);
		lower_panel.add(return_button);
	}


}
