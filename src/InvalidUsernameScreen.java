
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * This class implements the Invalid Username Screen that appears if users attempt to log in with an existing username 
 * that doesn't exist in the database
 * @authors Tian Lee, Paul Price, Christian Kennedy
 *
 */
public class InvalidUsernameScreen extends MenuOutline{
	
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
		JLabel invalid_username = createLabel("invalidusername.png");
		middle_panel.add(invalid_username);
		//************
		JButton enter_valid_button = createButton("entervalidusername.png");
		enter_valid_button.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	current_frame.dispose();
		    	LoginEntryScreen login_entry = new LoginEntryScreen();
		    }
		});	
		//***********
		lower_panel.add(enter_valid_button);
	}
	

}
