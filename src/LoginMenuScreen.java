
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * This class implements the first screen the user sees, the Login Menu Screen
 * @authors Tian Lee, Paul Price, Christian Kennedy
 *
 */
public class LoginMenuScreen extends MenuOutline{

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
		JButton login_button = createButton("login.png");
		login_button.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	current_frame.dispose();
		    	LoginEntryScreen login_entry = new LoginEntryScreen();
		    }
		});	
		//******
		JButton newuser_button = createButton("newuser.png");
		newuser_button.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	current_frame.dispose();
		    	NewUserScreen new_user = new NewUserScreen();
		    }
		});	
		//*********
		JButton exit_button = createButton("exit.png");
		exit_button.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	current_frame.dispose();
		    	current_frame.dispatchEvent(new WindowEvent(current_frame, WindowEvent.WINDOW_CLOSING));
	        }
	  	});	
		//**********
		middle_panel.add(login_button);
		middle_panel.add(newuser_button);
		lower_panel.add(exit_button);
	}

}
