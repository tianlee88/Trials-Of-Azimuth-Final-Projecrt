
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
 * This class implements the Username Taken screen, which appears if a new user attempts to enter a username
 * that already exists in the database for a different user
 * @authors Tian Lee, Paul Price, Christian Kennedy
 *
 */
public class UsernameTakenScreen extends MenuOutline{
	
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
		JLabel username_taken = createLabel("usernametaken.png");
		middle_panel.add(username_taken);
		//************
		JButton enter_different_button = createButton("enterdifferentusername.png");
		enter_different_button.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	current_frame.dispose();
		    	NewUserScreen new_user = new NewUserScreen();
		    }
		});	
		//***********
		lower_panel.add(enter_different_button);
	}
	

}
